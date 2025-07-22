package com.gamingroom.gameauth;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.ws.rs.client.Client;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gamingroom.gameauth.auth.GameAuthenticator;
import com.gamingroom.gameauth.auth.GameAuthorizer;
import com.gamingroom.gameauth.auth.GameUser;
import com.gamingroom.gameauth.controller.GameUserRESTController;
import com.gamingroom.gameauth.controller.RESTClientController;
import com.gamingroom.gameauth.healthcheck.AppHealthCheck;
import com.gamingroom.gameauth.healthcheck.HealthCheckController;

/**
 * Main application class responsible for starting the Dropwizard service.
 * This class initializes the application, registers resources, sets up security,
 * and runs health checks.
 */
public class GameAuthApplication extends Application<Configuration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameAuthApplication.class);

    @Override
    public void initialize(Bootstrap<Configuration> b) {
        // Nothing special to initialize at the moment
    }

    @Override
    public void run(Configuration c, Environment e) throws Exception {
        LOGGER.info("Starting GameAuth Application and registering REST resources...");

        // Create a Jersey client for internal REST API calls
        // This client will be used by our RESTClientController to call endpoints
        Client client = new JerseyClientBuilder(e).build("DemoRESTClient");

        // Register the REST controllers with the environment
        // GameUserRESTController handles user-related operations
        // RESTClientController demonstrates consuming an internal API using the client
        e.jersey().register(new GameUserRESTController(e.getValidator()));
        e.jersey().register(new RESTClientController(client));

        // Register a custom health check to monitor the application health
        e.healthChecks().register("APIHealthCheck", new AppHealthCheck(client));

        // Enable multiple health checks using a controller
        e.jersey().register(new HealthCheckController(e.healthChecks()));

        // Configure Basic Authentication and Role-based Authorization
        // AuthDynamicFeature ties the authenticator and authorizer to incoming requests
        e.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<GameUser>()
                        .setAuthenticator(new GameAuthenticator())  // Validate credentials
                        .setAuthorizer(new GameAuthorizer())        // Check user roles
                        .setRealm("App Security")                   // Realm name for authentication
                        .buildAuthFilter()));
        
        // Allows injecting @Auth GameUser in resource methods
        e.jersey().register(new AuthValueFactoryProvider.Binder<>(GameUser.class));

        // Enables @RolesAllowed annotation to work with our security
        e.jersey().register(RolesAllowedDynamicFeature.class);
    }

    public static void main(String[] args) throws Exception {
        // Start the application with arguments (expects config.yml)
        new GameAuthApplication().run(args);
    }
}
