package com.wollace.wealthPilot.model.appUser;

import com.wollace.wealthPilot.dto.CreateAppUserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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
    public AppUser(CreateAppUserDto createAppUserDto) {
        this.financialGoal = createAppUserDto.financialGoal();
        this.firebaseUid = createAppUserDto.firebaseUid();
        this.riskProfile = createAppUserDto.riskProfile();
    }
}
