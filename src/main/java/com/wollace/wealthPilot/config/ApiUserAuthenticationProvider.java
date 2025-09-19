package com.wollace.wealthPilot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ApiUserAuthenticationProvider implements AuthenticationProvider {
    // Variables
    private final UserDetailsService apiUserDetailsService;
    //private final PasswordEncoder passwordEncoder;
    // Functions
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails apiUser = apiUserDetailsService.loadUserByUsername(username);
        if(!password.equals(apiUser.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }
        return new UsernamePasswordAuthenticationToken(
                apiUser.getUsername(),
                apiUser.getPassword(),
                apiUser.getAuthorities()
                );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }


    // Constructor
    @Autowired
    public ApiUserAuthenticationProvider(UserDetailsService apiUserDetailsService) {
        this.apiUserDetailsService = apiUserDetailsService;
        //this.passwordEncoder = passwordEncoder;
    }
}
