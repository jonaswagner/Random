package contractfirstservice;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import contractfirstgenerated.*;

@Path( "/contractfirst" )
public class ContractfirstService
{
    @POST
    @Consumes( MediaType.TEXT_XML )
    @Produces( MediaType.TEXT_XML )
    public ResultTO doContractfirstService( InputTO inp )
    {
        ResultTO res = new ResultTO();
        res.setI( inp.getI() * 2 );
        res.setS( inp.getS() + " - ret" );
        return res;
    }
}