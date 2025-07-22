package com.gamingroom.gameauth.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * Authenticator that validates username and password for Basic Authentication.
 * Uses a simple in-memory map of users and roles.
 */
public class GameAuthenticator implements Authenticator<BasicCredentials, GameUser> {

    // Predefined users and their roles
    private static final Map<String, Set<String>> VALID_USERS = ImmutableMap.of(
            "admin", ImmutableSet.of("ADMIN", "USER"),
            "user", ImmutableSet.of("USER"),
            "player", ImmutableSet.of("USER"),
            "guest", ImmutableSet.of("GUEST")
    );

    @Override
    public Optional<GameUser> authenticate(BasicCredentials credentials) throws AuthenticationException {
        // Validate password and username
        if ("password".equals(credentials.getPassword()) && VALID_USERS.containsKey(credentials.getUsername())) {
            // Return authenticated user with roles
            return Optional.of(new GameUser(credentials.getUsername(), VALID_USERS.get(credentials.getUsername())));
        }
        // If credentials do not match, return empty
        return Optional.empty();
    }
}
