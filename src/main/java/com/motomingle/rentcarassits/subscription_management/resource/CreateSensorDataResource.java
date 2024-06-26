package com.motomingle.rentcarassits.subscription_management.resource;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateSensorDataResource {
    @NotNull
    private double temperature;

    @NotNull
    private double humidity;
}
