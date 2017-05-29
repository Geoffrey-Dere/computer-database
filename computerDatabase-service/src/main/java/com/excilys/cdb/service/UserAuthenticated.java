package com.excilys.cdb.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserAuthenticated extends User {

    /**
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param username the username
     * @param password password
     * @param enabled enabled
     * @param accountNonExpired accountNonExpired
     * @param credentialsNonExpired credentialsNonExpired
     * @param authorities authorities
     * @throws IllegalArgumentException IllegalArgumentException
     */
    public UserAuthenticated(String username, String password, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, Collection<? extends GrantedAuthority> authorities)
            throws IllegalArgumentException {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, true, authorities);
    }
}
