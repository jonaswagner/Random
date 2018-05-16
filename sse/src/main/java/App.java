import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class App {

    public static void main(String[] args) {

        String baseUrl = (args.length > 0) ? args[0] : "http://localhost:4434";

        ResourceConfig config = new ResourceConfig(SseResource.class);

        final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(
                URI.create(baseUrl), config, false);

        try {
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    server.shutdownNow();
                }
            }));
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
