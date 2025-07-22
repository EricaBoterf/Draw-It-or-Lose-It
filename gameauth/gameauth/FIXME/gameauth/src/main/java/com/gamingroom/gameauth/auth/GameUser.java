package com.gamingroom.gameauth.auth;

import java.security.Principal;
import java.util.Set;

/**
 * Represents an authenticated user in the system.
 * Implements Principal so it can integrate with Dropwizard security.
 *
 * This class holds:
 * - The username (identity of the user)
 * - The roles assigned to the user (used for role-based access control)
 */
public class GameUser implements Principal {

    private String name;        // The username of the authenticated user
    private Set<String> roles;  // Roles associated with this user

    /**
     * Constructor to create a GameUser with username and roles.
     *
     * @param name  the username
     * @param roles the set of roles assigned to the user
     */
    public GameUser(String name, Set<String> roles) {
        this.name = name;
        this.roles = roles;
    }

    /**
     * Required by Principal interface.
     * Returns the username of this user.
     *
     * @return username as a String
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns all roles assigned to this user.
     *
     * @return a Set of role names (e.g., ADMIN, USER)
     */
    public Set<String> getRoles() {
        return roles;
    }

    /**
     * Checks if the user has a specific role.
     *
     * @param role the role to check
     * @return true if user has the role, otherwise false
     */
    public boolean hasRole(String role) {
        return roles.contains(role);
    }

    @Override
    public String toString() {
        return "GameUser{name='" + name + "', roles=" + roles + "}";
    }
}
