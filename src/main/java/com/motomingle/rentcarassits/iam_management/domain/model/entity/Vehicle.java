package com.motomingle.rentcarassits.iam_management.domain.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "model")
    private String model;

    @NotBlank
    @Column(name = "brand")
    private String brand;

    @NotBlank
    @Column(name = "address")
    private String address;

    @Min(1)
    @NotNull
    @Column(name = "seats")
    private int seats;

    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "5.0", inclusive = true)
    @NotNull
    @Column(name = "stars")
    private float stars;

    @NotBlank
    @Column(name = "features")
    private String features;

    @NotNull
    @Min(0)
    @Column(name = "price")
    private float price;

    @NotBlank
    @Column(name = "vehicle_picture")
    private String vehiclePicture;
}
