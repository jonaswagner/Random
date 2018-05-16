package minirestwebservice;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.naming.InitialContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path(HalloWeltService.webContextPath)
public class HalloWeltService {
    static final String webContextPath = "/helloworld";

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String halloPlainText(@QueryParam("name") String name) {
        return "Plain-Text: Hallo " + name;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String halloHtml(@QueryParam("name") String name) {
        return "<html><title>HelloWorld</title><body><h2>Html: Hallo " + name + "</h2></body></html>";
    }

    @GET
    @Path("/events")
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    public EventOutput getServerSentEvents() {
        final EventOutput eventOutput = new EventOutput();

        ManagedExecutorService svc = null;
        try {
            InitialContext ctx = new InitialContext();
            svc = (ManagedExecutorService)
                    ctx.lookup("java:comp/env/concurrent/ThreadPool");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        // ... code that waits 1 second
                        final OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
                        eventBuilder.name("message-to-client");
                        eventBuilder.data(String.class, "Hello world " + i + "!");
                        final OutboundEvent event = eventBuilder.build();
                        eventOutput.write(event);
                        Thread.sleep(300);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Error when writing the event.", e);
                } finally {
                    try {
                        eventOutput.close();
                    } catch (IOException ioClose) {
                        throw new RuntimeException("Error when closing the event output.", ioClose);
                    }
                }
            }
        };

        svc.submit(runnable);

        return eventOutput;
    }

}
