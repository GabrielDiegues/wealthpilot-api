package com.wollace.wealthPilot.service;

import com.wollace.wealthPilot.dto.UserDto;
import com.wollace.wealthPilot.model.User;
import com.wollace.wealthPilot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    // Variables
    private final UserRepository userCrud;
    private final PasswordEncoder encoder;

    // Functions
    private User toEntity(final UserDto dto) {
        final User user = new User();
        user.setEmail(dto.email());
        user.setPassword(encoder.encode(dto.password()));
        return user;
    }


    public ResponseEntity<Boolean> checkUser(final UserDto user) {
        Optional<User> userByEmail = userCrud.findByEmail(user.email());
        boolean isValid = userByEmail.isPresent() && user.password().equals(userByEmail.get().getPassword());
        return new ResponseEntity<>(isValid, HttpStatus.OK);
    }


    public ResponseEntity<Boolean> createUser(final UserDto user) {
        try {

            userCrud.save(toEntity(user));
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Constructor
    @Autowired
    public UserService(final UserRepository userCrud, final PasswordEncoder encoder) {
        this.userCrud = userCrud;
        this.encoder = encoder;
    }
}
