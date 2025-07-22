package com.gamingroom.gameauth.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST client controller that consumes internal APIs using Jersey Client.
 * Demonstrates making an HTTP request to our own service.
 */
@Path("/client")
@Produces(MediaType.TEXT_PLAIN)
public class RESTClientController {

    private Client client;

    // Injecting the Jersey client built in GameAuthApplication
    public RESTClientController(Client client) {
        this.client = client;
    }

    /**
     * Fetch the list of game users by calling the /gameusers endpoint internally.
     */
    @GET
    @Path("/gameusers")
    public String getGameUsers() {
        // Define the target URL for internal API call
        WebTarget webTarget = client.target("http://localhost:8080/gameusers");

        // Build the request and get the response
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();

        // Return the response as a string
        return response.readEntity(String.class);
    }
}
