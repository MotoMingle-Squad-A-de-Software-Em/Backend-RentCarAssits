package com.motomingle.rentcarassits.iam_management.resource;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVehicleResource {

    private Long id;

    @NotBlank
    private String model;

    @NotBlank
    private String brand;

    @NotBlank
    private String address;

    @Min(1)
    @NotNull
    private int seats;

    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "5.0", inclusive = true)
    @NotNull
    private float stars;

    @NotBlank
    private String features;

    @NotNull
    @Min(0)
    private float price;

    @NotBlank
    private String vehiclePicture;
}
