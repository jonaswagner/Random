package integrationstest;

import javax.ws.rs.client.*;
import javax.xml.bind.JAXBElement;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.*;
import contractfirstgenerated.*;

public class JaxRsAuthenticationIntegrTest
{
    @Test
    public void testJaxRsAuthentication() throws Exception
    {
        // Testclient:
        String xmlUtf8        = "text/xml; charset=utf-8";
        String baseUrl        = "http://localhost:8080/JaxRsAuthentication/rest";
        String webContextPath = "/contractfirst";
        String usr            = "MeinName";
        String pwd            = "MeinPasswort";

        Client c = ClientBuilder.newClient();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic( usr, pwd );
        c.register( feature );
        WebTarget target = c.target( baseUrl );

        // Mit JAXB und mit bequemen Java-Objekten:
        InputTO inpTO = new InputTO();
        inpTO.setI( 42 );
        inpTO.setS( "abc xyz" );
        JAXBElement<InputTO> inpJaxb = (new ObjectFactory()).createInputTO( inpTO );
        ResultTO resTO = target.path( webContextPath ).request( xmlUtf8 ).accept( xmlUtf8 ).post(
                Entity.entity( inpJaxb, xmlUtf8 ), ResultTO.class );
        Assert.assertEquals(  84,             resTO.getI() );
        Assert.assertEquals( "abc xyz - ret", resTO.getS() );

        // Ohne JAXB und mit XML-Strings:
        String resXml = target.path( webContextPath ).request( xmlUtf8 ).accept( xmlUtf8 ).post(
                Entity.entity( "<inputTO xmlns=\"mein.ns\"><i>42</i><s>abc xyz</s></inputTO>", xmlUtf8 ), String.class );
        Assert.assertEquals( "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                "<resultTO xmlns=\"mein.ns\"><i>84</i><s>abc xyz - ret</s></resultTO>", resXml );

        // Eventuell verzoegerte Beendigung (siehe Text):
        // Thread.sleep( 20000 );
    }
}