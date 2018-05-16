package xmljaxb;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/xmljaxb" )
public class XmlJaxbService
{
   @POST
   @Consumes( MediaType.TEXT_XML )
   @Produces( MediaType.TEXT_XML )
   public ResultTO doXmlJaxbService( InputTO inp )
   {
      ResultTO res = new ResultTO();
      res.i = inp.i * 2;
      res.s = inp.s + " - ret";
      return res;
   }
}