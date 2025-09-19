package com.wollace.wealthPilot.model.appUser;

import com.wollace.wealthPilot.dto.AppUserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    // Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String firebaseUid;

    @Column(nullable = false)
    private String financialGoal;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RiskProfile riskProfile;



    // UserDto to User constructor
    public AppUser(AppUserDto appUserDto) {
        this.financialGoal = appUserDto.financialGoal();
        this.firebaseUid = appUserDto.firebaseUid();
        this.riskProfile = appUserDto.riskProfile();
    }
}
