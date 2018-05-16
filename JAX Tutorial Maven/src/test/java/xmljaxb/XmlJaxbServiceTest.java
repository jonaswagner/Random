package xmljaxb;

import java.net.URI;
import javax.ws.rs.client.*;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.*;

public class XmlJaxbServiceTest
{
    @Test
    public void testXmlJaxbService() throws InterruptedException {
        String xmlUtf8        = "text/xml; charset=utf-8";
        String baseUrl        = "http://localhost:4434";
        String webContextPath = "/xmljaxb";

        // Testserver:
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(
                URI.create( baseUrl ), new ResourceConfig( XmlJaxbService.class ) );

        try {
            // Testclient:
            Client c = ClientBuilder.newClient();
            WebTarget target = c.target( baseUrl );

            // Mit JAXB und mit bequemen Java-Objekten:
            InputTO inpTO = new InputTO();
            inpTO.i = 42;
            inpTO.s = "abc xyz";
            ResultTO resTO = target.path( webContextPath ).request( xmlUtf8 ).accept( xmlUtf8 ).post(
                    Entity.entity( inpTO, xmlUtf8 ), ResultTO.class );
            Assert.assertEquals(  84,             resTO.i );
            Assert.assertEquals( "abc xyz - ret", resTO.s );

            // Ohne JAXB und mit XML-Strings:
            String resXml = target.path( webContextPath ).request( xmlUtf8 ).accept( xmlUtf8 ).post(
                    Entity.entity( "<inputTO><i>42</i><s>abc xyz</s></inputTO>", xmlUtf8 ), String.class );
            Assert.assertEquals( "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                    "<resultTO><i>84</i><s>abc xyz - ret</s></resultTO>", resXml );

        } finally {
            // Eventuell verzoegerte Beendigung:
            // try {  Thread.sleep( 20000 ); } catch( java.lang.InterruptedException e ) { /* ok */ }
            // Testserver beenden:
            server.shutdown();
        }
    }
}