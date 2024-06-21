package com.motomingle.rentcarassits.iam_management.resource;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class BookingResource {
    private Long id;
    private Long userId;
    private Long vehicleId;
    private LocalDate bookingDate;
    private LocalDate returnDate;
}