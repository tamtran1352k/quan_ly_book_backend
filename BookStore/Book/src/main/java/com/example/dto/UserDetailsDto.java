package com.example.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserDetailsDto extends User {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UserDetailsDto(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

    }


}
