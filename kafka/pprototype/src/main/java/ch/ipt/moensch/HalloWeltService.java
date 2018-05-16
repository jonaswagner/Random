package ch.ipt.moensch;

import org.glassfish.grizzly.http.util.TimeStamp;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;

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

    @POST
    @Path("/sse")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public EventOutput getServerSentEvents(PRequest request) {
        final EventOutput eventOutput = new EventOutput();

        new Thread(() -> {

            try {

                int kafkaId = 0;

                do {
                    final OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
                    eventBuilder.name("pprototype-update: " + new TimeStamp().toString());
                    eventBuilder.id("" + kafkaId);
                    eventBuilder.data(String.class, "kafka-fact #" + kafkaId);
                    final OutboundEvent event = eventBuilder.build();
                    eventOutput.write(event);
                    Thread.sleep(300);
                    kafkaId++;
                } while (true);
            } catch (Exception e) {
                throw new RuntimeException("Error when writing the event.", e);
            } finally {
                try {
                    eventOutput.close();
                } catch (IOException ioClose) {
                    throw new RuntimeException("Error when closing the event output.", ioClose);
                }
            }
        }).start();

        return eventOutput;
    }
}