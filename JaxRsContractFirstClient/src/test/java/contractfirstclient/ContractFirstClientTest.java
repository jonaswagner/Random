package contractfirstclient;

import org.junit.*;
import contractfirstgenerated.*;
import contractfirstservice.ContractfirstService;
import contractfirstservice.RestServerTestUtil;

public class ContractFirstClientTest
{
    static final String BASE_URL         = "http://localhost:4434";
    static final String WEB_CONTEXT_PATH = "/contractfirst";

    @Test
    public void testContractFirstClient()
    {
        InputTO inpTO = new InputTO();
        inpTO.setI( 42 );
        inpTO.setS( "abc xyz" );

        // Das REST-Server-Test-Util startet den Grizzly-Webserver:
        try( RestServerTestUtil restServerTestUtil = new RestServerTestUtil( BASE_URL, ContractfirstService.class ) )
        {
            // Aufruf des REST-Services ueber den REST-Client:
            ResultTO resTO = ContractFirstClient.callContractfirstService( BASE_URL, WEB_CONTEXT_PATH, inpTO );

            Assert.assertEquals(  84,             resTO.getI() );
            Assert.assertEquals( "abc xyz - ret", resTO.getS() );
        }
    }
}