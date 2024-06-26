package com.motomingle.rentcarassits.subscription_management.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class SensorDataResource {
    private Long sensorDataId;
    private double temperature;
    private double humidity;
}
