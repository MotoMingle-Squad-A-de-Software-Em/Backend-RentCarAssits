package com.motomingle.rentcarassits.iam_management.resource;


import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingResource {

    @NotNull
    private Long userId;

    @NotNull
    private Long vehicleId;

    @NotNull
    private LocalDate bookingDate;

    @NotNull
    private LocalDate returnDate;
}