package com.wollace.wealthPilot.service.appUser;

import com.wollace.wealthPilot.dto.AppUserDto;
import com.wollace.wealthPilot.model.appUser.AppUser;
import com.wollace.wealthPilot.repository.appUser.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
    // Variables
    private final AppUserRepository appUserCrud;

    // Functions

    public ResponseEntity<String> createUser(final AppUserDto appUser) {
        try {
            appUserCrud.save(new AppUser(appUser));
            return new ResponseEntity<>("User created with sucess", HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>("Error storing the user\n" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Boolean> isAppUserRegistered(String firebaseUid) {
        return appUserCrud.existsByFirebaseUid(firebaseUid) ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(false, HttpStatus.OK);
    }

    // Constructor
    @Autowired
    public AppUserService(final AppUserRepository appUserCrud) {
        this.appUserCrud = appUserCrud;
    }
}
