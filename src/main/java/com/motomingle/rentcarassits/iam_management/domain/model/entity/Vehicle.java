package com.motomingle.rentcarassits.iam_management.domain.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @Column(name = "model")
    private String model;

    @NotBlank
    @NotNull
    @Column(name = "brand")
    private String brand;

    @NotBlank
    @NotNull
    @Column(name = "address")
    private String address;

    @NotBlank
    @NotNull
    @Column(name = "seats")
    private int seats;

    @NotBlank
    @NotNull
    @Column(name = "stars")
    private float stars;

    @NotBlank
    @NotNull
    @Column(name = "features")
    private String features;

    @NotBlank
    @NotNull
    @Column(name = "price")
    private float price;

    @NotBlank
    @NotNull
    @Column(name = "vehicle_picture")
    private String vehiclePicture;
}
