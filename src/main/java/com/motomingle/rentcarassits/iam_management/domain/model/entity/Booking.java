package com.motomingle.rentcarassits.iam_management.domain.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Column(name = "vehicle_id")
    private Long vehicleId;

    @NotNull
    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @NotNull
    @Column(name = "return_date")
    private LocalDate returnDate;
}