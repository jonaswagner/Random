package ch.ipt.moensch;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class HalloWeltTestClient {
    public static void main(String[] args) {
        String name = (args.length > 0) ? args[0] : "ich";
        String baseUrl = (args.length > 1) ? args[1] : "http://localhost:4434";
        String webContextPath = "/helloworld";
        System.out.println("\nAngefragte URL: " + baseUrl + webContextPath + "?name=" + name);

        Client c = ClientBuilder.newClient();
        WebTarget target = c.target(baseUrl);
/*
        System.out.println("\nTextausgabe:");
        System.out.println(target.path(webContextPath).queryParam("name", name).request(MediaType.TEXT_PLAIN).get(String.class));
        System.out.println("\nHTML-Ausgabe:");
        System.out.println(target.path(webContextPath).queryParam("name", name).request(MediaType.TEXT_HTML).get(String.class));
*/
        PRequest pRequest = new PRequest();
        pRequest.setId(1234);
        pRequest.setRequest("Hi Mom!");

        WebTarget webTarget = c.target(baseUrl);
        System.out.println("\nJSON:");
        Invocation.Builder invocationBuilder
                = webTarget.path("/helloworld/sse").request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder
                .post(Entity.entity(pRequest, MediaType.APPLICATION_JSON));

        System.out.println(response);
    }
}