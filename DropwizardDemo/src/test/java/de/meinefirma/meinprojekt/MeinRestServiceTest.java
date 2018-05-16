package de.meinefirma.meinprojekt;

import io.dropwizard.testing.junit.DropwizardClientRule;
import javax.ws.rs.client.*;
import org.junit.*;

public class MeinRestServiceTest
{
    private static final String MEIN_CONFIG_PARM = "Test-Parm";
    private final Client client = ClientBuilder.newClient();

    @ClassRule
    public final static DropwizardClientRule dwClntRule = new DropwizardClientRule( new MeinRestService( MEIN_CONFIG_PARM ) );

    @Test
    public void testeMeinRestService() throws Exception
    {
        WebTarget target = client.target( dwClntRule.baseUri() ).path( MeinRestService.meinWebContextPfad );
        ResultTO response = target.request().get( ResultTO.class );
        Assert.assertEquals( MEIN_CONFIG_PARM, response.getMeinConfigParm() );
    }
}