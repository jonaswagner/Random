package contractfirstservice;

import java.net.URI;
import javax.ws.rs.client.*;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class RestServerTestUtil implements AutoCloseable
{
    private String baseUrlClnt;
    private HttpServer server;

    // Fuer normale Modultests:
    public RestServerTestUtil( String baseUrl, Class<?>... restServiceClasses )
    {
        this( baseUrl, baseUrl, restServiceClasses );
    }

    // Falls ein HTTP-Monitor oder TCP/IP-Monitor zwischengeschaltet werden soll:
    public RestServerTestUtil( String baseUrlSrvr, String baseUrlClnt, Class<?>... restServiceClasses )
    {
        // Grizzly-Testserver starten:
        this.baseUrlClnt = baseUrlClnt;
        server = GrizzlyHttpServerFactory.createHttpServer(
                URI.create( baseUrlSrvr ), new ResourceConfig( restServiceClasses ) );
    }

    // Testclient:
    public WebTarget geWebTarget()
    {
        return ClientBuilder.newClient().target( baseUrlClnt );
    }

    @Override
    public void close()
    {
        if( server != null ) {
            // Eventuell verzoegerte Beendigung:
            // try {  Thread.sleep( 20000 ); } catch( java.lang.InterruptedException e ) { /* ok */ }
            // Grizzly-Testserver beenden:
            server.shutdown();
            server = null;
        }
    }
}