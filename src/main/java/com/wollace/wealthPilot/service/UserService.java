package com.wollace.wealthPilot.service;

import com.wollace.wealthPilot.model.User;
import com.wollace.wealthPilot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    // Variables
    private final UserRepository userCrud;

    // Functions
    public ResponseEntity<Boolean> checkUser(User user) {
        Optional<User> userByEmail = userCrud.findByEmail(user.getEmail());
        boolean isValid =  userByEmail.isPresent() && user.getPassword().equals(userByEmail.get().getPassword());
        return new ResponseEntity<>(isValid, HttpStatus.OK);
    }


    public ResponseEntity<Boolean> createUser(User user) {
        try {
            userCrud.save(user);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Constructor
    @Autowired
    public UserService(UserRepository userCrud) {
        this.userCrud = userCrud;
    }
}
