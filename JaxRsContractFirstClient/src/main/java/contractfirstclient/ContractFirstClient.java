package contractfirstclient;

import javax.ws.rs.client.*;
import javax.xml.bind.JAXBElement;
import contractfirstgenerated.*;

public class ContractFirstClient
{
    static final String XML_UTF8 = "text/xml; charset=utf-8";

    public static void main( String[] args )
    {
        System.out.println( "Es werden vier Parameter benoetigt: Basis-URL, Web-Context-Pfad, i, s.\n"
                + "Z.B.: http://localhost:8080 /JaxRsContractFirstService/rest/contractfirst 33 aa" );
        if( args.length == 4 ) {
            ResultTO resTO = callContractfirstService( args[0], args[1], Integer.parseInt( args[2] ), args[3] );
            System.out.println( "Ergebnis: resTO.i=" + resTO.getI() + ", resTO.s=" + resTO.getS() );
        }
    }

    public static ResultTO callContractfirstService( String baseUrl, String webContextPath, int i, String s )
    {
        InputTO inpTO = new InputTO();
        inpTO.setI( i );
        inpTO.setS( s );
        return callContractfirstService( baseUrl, webContextPath, inpTO );
    }

    public static ResultTO callContractfirstService( String baseUrl, String webContextPath, InputTO inpTO )
    {
        WebTarget target = ClientBuilder.newClient().target( baseUrl );

        JAXBElement<InputTO> inpJaxb = (new ObjectFactory()).createInputTO( inpTO );

        // HTTP-POST zum REST-Service:
        return target.path( webContextPath ).request( XML_UTF8 ).accept( XML_UTF8 ).post(
                Entity.entity( inpJaxb, XML_UTF8 ), ResultTO.class );
    }
}