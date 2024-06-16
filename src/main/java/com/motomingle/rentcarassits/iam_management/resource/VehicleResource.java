package com.motomingle.rentcarassits.iam_management.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResource {
    private Long id;
    private String model;
    private String brand;
    private String address;
    private int seats;
    private float stars;
    private String features;
    private float price;
    private String vehiclePicture;
}
