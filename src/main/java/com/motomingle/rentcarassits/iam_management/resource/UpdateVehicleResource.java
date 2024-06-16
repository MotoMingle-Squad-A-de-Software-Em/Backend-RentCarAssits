package com.motomingle.rentcarassits.iam_management.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVehicleResource {

    private Long id;

    @NotBlank
    @NotNull
    private String model;

    @NotBlank
    @NotNull
    private String brand;

    @NotBlank
    @NotNull
    private String address;

    @NotBlank
    @NotNull
    private int seats;

    @NotBlank
    @NotNull
    private float stars;

    @NotBlank
    @NotNull
    private String features;

    @NotBlank
    @NotNull
    private float price;

    @NotBlank
    @NotNull
    private String vehiclePicture;
}
