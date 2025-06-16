package com.wollace.wealthPilot.controller;

import com.wollace.wealthPilot.model.User;
import com.wollace.wealthPilot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {
    // Variables
    private final UserService service;

    // Post requests
    @PostMapping("/login")
    public ResponseEntity<Boolean> attemptLogin(@RequestBody User user) {
        return service.checkUser(user);
    }


    @PostMapping("/signup")
    public ResponseEntity<Boolean> createUser(@RequestBody User user) {
        return service.createUser(user);
    }
    // Constructor
    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }
}
