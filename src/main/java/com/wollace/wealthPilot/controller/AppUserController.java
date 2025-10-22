package com.wollace.wealthPilot.controller;

import com.wollace.wealthPilot.dto.CreateAppUserDto;
import com.wollace.wealthPilot.dto.AppUserDto;
import com.wollace.wealthPilot.dto.UpdateAppUser;
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
    public ResponseEntity<String> createUser(@RequestBody CreateAppUserDto appUser) {
        return service.createAppUser(appUser);
    }


    // Get requests
    @GetMapping("/exists")
    public ResponseEntity<Boolean> isUserRegistered(@RequestParam String firebaseUid) {
        return service.isAppUserRegistered(firebaseUid);
    }


    @GetMapping("/{uid}")
    public ResponseEntity<AppUserDto> getUser(@PathVariable String uid) {
        return service.getAppUser(uid);
    }


    // Patch Requests
    @PatchMapping("/patch")
    public ResponseEntity<String> patchUser(@RequestBody UpdateAppUser appUser) {
        return service.patchAppUser(appUser);
    }


    // Delete requests
    @DeleteMapping("/delete/{uid}")
    public ResponseEntity<String> deleteUser(@PathVariable String uid) {
        return service.deleteAppUser(uid);
    }

    // Constructor
    @Autowired
    public AppUserController(AppUserService service) {
        this.service = service;
    }
}
