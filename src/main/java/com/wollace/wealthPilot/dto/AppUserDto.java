package com.wollace.wealthPilot.dto;

import com.wollace.wealthPilot.model.appUser.RiskProfile;
import com.wollace.wealthPilot.model.appUser.AppUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AppUserDto(
        @NotBlank
        String firebaseUid,
        @NotBlank
        String financialGoal,
        @NotNull
        RiskProfile riskProfile
        ) {
        public AppUserDto(AppUser appUser) {
                this(appUser.getFirebaseUid(), appUser.getFinancialGoal(), appUser.getRiskProfile());
        }
}
