package com.wollace.wealthPilot.service.apiUser;

import com.wollace.wealthPilot.model.apiUser.ApiUser;
import com.wollace.wealthPilot.repository.apiUser.ApiUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApiUserService {
    private final ApiUserRepository apiUserCrud;

    // Functions
    public Optional<ApiUser> getApiUserByUserName(String userName) {
        return apiUserCrud.findByUserName(userName);
    }
    // Constructor
    @Autowired
    public ApiUserService(ApiUserRepository apiUserCrud) {
        this.apiUserCrud = apiUserCrud;
    }
}
