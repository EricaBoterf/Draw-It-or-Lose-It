package com.gamingroom.gameauth.controller;

import io.dropwizard.auth.Auth;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.gamingroom.gameauth.auth.GameUser;
import com.gamingroom.gameauth.dao.GameUserDB;
import com.gamingroom.gameauth.representations.GameUserInfo;

/**
 * REST controller for managing Game Users.
 * This class demonstrates how role-based security is applied using annotations.
 */
@Path("/gameusers") // Base URL path for all endpoints in this controller
@Produces(MediaType.APPLICATION_JSON)
public class GameUserRESTController {

    private final Validator validator;

    // Injecting the validator for request validation
    public GameUserRESTController(Validator validator) {
        this.validator = validator;
    }

    /**
     * Fetch all game users.
     * Accessible by all authenticated users.
     */
    @PermitAll
    @GET
    public Response getGameUsers(@Auth GameUser user) {
        return Response.ok(GameUserDB.getGameUsers()).build();
    }

    /**
     * Fetch a single game user by ID.
     * Only users with the "USER" role are allowed.
     */
    @RolesAllowed("USER")
    @GET
    @Path("/{id}")
    public Response getGameUserById(@PathParam("id") Integer id, @Auth GameUser user) {
        GameUserInfo gameUserInfo = GameUserDB.getGameUser(id);
        if (gameUserInfo != null) {
            return Response.ok(gameUserInfo).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    /**
     * Create or update a game user.
     * Only users with the "ADMIN" role are allowed.
     */
    @RolesAllowed("ADMIN")
    @POST
    public Response createGameUser(GameUserInfo gameUserInfo, @Auth GameUser user) throws URISyntaxException {
        // Validate the incoming data
        Set<ConstraintViolation<GameUserInfo>> violations = validator.validate(gameUserInfo);
        GameUserInfo existing = GameUserDB.getGameUser(gameUserInfo.getId());

        if (!violations.isEmpty()) {
            ArrayList<String> validationMessages = new ArrayList<>();
            for (ConstraintViolation<GameUserInfo> violation : violations) {
                validationMessages.add(violation.getPropertyPath() + ": " + violation.getMessage());
            }
            return Response.status(Status.BAD_REQUEST).entity(validationMessages).build();
        }

        if (existing != null) {
            // Update the existing user
            GameUserDB.updateGameUser(gameUserInfo.getId(), gameUserInfo);
            return Response.created(new URI("/gameusers/" + gameUserInfo.getId())).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }
}
