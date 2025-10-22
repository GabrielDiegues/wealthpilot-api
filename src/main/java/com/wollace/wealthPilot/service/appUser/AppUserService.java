package com.wollace.wealthPilot.service.appUser;

import com.wollace.wealthPilot.dto.CreateAppUserDto;
import com.wollace.wealthPilot.dto.AppUserDto;
import com.wollace.wealthPilot.dto.UpdateAppUser;
import com.wollace.wealthPilot.model.appUser.AppUser;
import com.wollace.wealthPilot.model.appUser.RiskProfile;
import com.wollace.wealthPilot.repository.appUser.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {
    // Variables
    private final AppUserRepository appUserCrud;

    // Functions

    public ResponseEntity<String> createAppUser(final CreateAppUserDto appUser) {
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


    public ResponseEntity<AppUserDto> getAppUser(String uid) {
        try {
            Optional<AppUser> optionalAppUser = appUserCrud.findByFirebaseUid(uid);
            if(optionalAppUser.isPresent()) {
               AppUser appUser = optionalAppUser.get();
               AppUserDto appUserDto = new AppUserDto(appUser);
               return new ResponseEntity<>(appUserDto, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<String> patchAppUser(UpdateAppUser appUser) {
        try {
            Optional<AppUser> optionalUser = appUserCrud.findByFirebaseUid(appUser.firebaseUid());
            if(optionalUser.isEmpty()) {
                return new ResponseEntity<>("Error! User not found", HttpStatus.NOT_FOUND);
            }

            AppUser registeredUser = optionalUser.get();
            if(appUser.financialGoal() != null && !appUser.financialGoal().isBlank()) {
                registeredUser.setFinancialGoal(appUser.financialGoal());
            }
            if(isRiskProfileValid(appUser.riskProfile())) {
                registeredUser.setRiskProfile(RiskProfile.valueOf(appUser.riskProfile()));
            }
            appUserCrud.save(registeredUser);
            return new ResponseEntity<>("User has been updated successfully", HttpStatus.OK);
        }
        catch(DataAccessException e) {
            return new ResponseEntity<>("Error connecting to the data base! Please, try again later\n" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e) {
            return new ResponseEntity<>("Unexpected data base error! Please, try again later\n" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<String> deleteAppUser(String uid) {
        try {
            Optional<AppUser> optionalUser = appUserCrud.findByFirebaseUid(uid);
            if(optionalUser.isEmpty()) {
                return new ResponseEntity<>("Error! User not found", HttpStatus.NOT_FOUND);
            }
            else {
                appUserCrud.deleteByFirebaseUid(uid);
                return new ResponseEntity<>("Success! User deleted with success", HttpStatus.OK);
            }
        }
        catch(DataAccessException e) {
            return new ResponseEntity<>("Error connecting to the data base! Please, try again later\n" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e) {
            return new ResponseEntity<>("Unexpected data base error! Please, try again later\n" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Private methods
    private boolean isRiskProfileValid(String value) {
        try {
            RiskProfile.valueOf(value.toLowerCase());
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }
    // Constructor
    @Autowired
    public AppUserService(final AppUserRepository appUserCrud) {
        this.appUserCrud = appUserCrud;
    }
}
