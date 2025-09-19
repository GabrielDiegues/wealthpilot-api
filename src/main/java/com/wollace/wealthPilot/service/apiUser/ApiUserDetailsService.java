package com.wollace.wealthPilot.service.apiUser;

import com.wollace.wealthPilot.model.apiUser.ApiUser;
import com.wollace.wealthPilot.model.apiUser.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApiUserDetailsService implements UserDetailsService {
    // Variables
    private final ApiUserService service;


    // Functions
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ApiUser> apiUser = service.getApiUserByUserName(username);
        if(apiUser.isEmpty()) {
            throw new UsernameNotFoundException("User name not found");
        }
        return new UserPrincipal(apiUser.get());
    }


    // Constructor
    @Autowired
    public ApiUserDetailsService(ApiUserService service) {
        this.service = service;
    }
}
