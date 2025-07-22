package com.gamingroom.gameauth.auth;

import io.dropwizard.auth.Authorizer;

/**
 * Authorizer that checks if the authenticated user has the required role.
 */
public class GameAuthorizer implements Authorizer<GameUser> {
    @Override
    public boolean authorize(GameUser user, String role) {
        // Grant access if user's roles contain the requested role
        return user.getRoles().contains(role);
    }
}
