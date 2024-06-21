package com.motomingle.rentcarassits.iam_management.domain.service;


import com.motomingle.rentcarassits.iam_management.domain.model.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingService {
    List<Booking> getAllBookings();
    Page<Booking> getAllBookings(Pageable pageable);
    Booking createBooking(Booking booking);
    Booking updateBooking(Long bookingId, Booking request);
    ResponseEntity<?> deleteBooking(Long bookingId);
    Booking getBookingById(Long bookingId);
}