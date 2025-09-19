package com.wollace.wealthPilot.model.apiUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserPrincipal implements UserDetails {
    // Variables
    private final ApiUser apiUser;
    // Functions
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("Admin"));
    }

    @Override
    public String getPassword() {
        return apiUser.getPassword();
    }

    @Override
    public String getUsername() {
        return apiUser.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    // Constructor
    @Autowired
    public UserPrincipal(ApiUser apiUser) {
        this.apiUser = apiUser;
    }
}
