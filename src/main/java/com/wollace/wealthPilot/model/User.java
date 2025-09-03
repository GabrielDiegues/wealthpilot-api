package com.wollace.wealthPilot.model;

import com.wollace.wealthPilot.dto.UserDto;
import jakarta.persistence.*;

@Entity
public class User {
    // Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;


    // Constructor
    public User(Long id, String email, String password) {
        this.email = email;
        this.password = password;
        this.id = id;
    }


    public User(UserDto userDto) {

    }

    public User() {
    }


    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(long id) { this.id = id; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
