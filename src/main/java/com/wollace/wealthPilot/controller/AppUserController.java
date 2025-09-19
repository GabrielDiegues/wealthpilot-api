package com.wollace.wealthPilot.controller;

import com.wollace.wealthPilot.dto.AppUserDto;
import com.wollace.wealthPilot.service.appUser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class AppUserController {
    // Variables
    private final AppUserService service;

    // Post requests
    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody AppUserDto appUser) {
        return service.createUser(appUser);
    }


    // Get requests
    @GetMapping("/exists")
    public ResponseEntity<Boolean> isUserRegistered(@RequestParam String firebaseUid) {
        return service.isAppUserRegistered(firebaseUid);
    }

    // Constructor
    @Autowired
    public AppUserController(AppUserService service) {
        this.service = service;
    }
}
