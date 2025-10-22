package com.wollace.wealthPilot.dto;

import com.wollace.wealthPilot.model.appUser.RiskProfile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateAppUser(@NotBlank
                            String firebaseUid,
                            @NotBlank
                            String financialGoal,
                            @NotNull
                            String riskProfile) {
}
