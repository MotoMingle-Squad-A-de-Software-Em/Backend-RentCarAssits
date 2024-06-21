package com.motomingle.rentcarassits.iam_management.service;

import com.motomingle.rentcarassits.iam_management.domain.model.entity.Booking;
import com.motomingle.rentcarassits.iam_management.domain.persistence.BookingRepository;
import com.motomingle.rentcarassits.iam_management.domain.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.motomingle.rentcarassits.shared.exception.ResourceNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

import java.util.List;
import java.util.Set;

@Service
public class BookingServiceImpl implements BookingService {

    private static final String ENTITY = "Booking";

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private Validator validator;

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Page<Booking> getAllBookings(Pageable pageable) {
        return bookingRepository.findAll(pageable);
    }

    @Override
    public Booking createBooking(Booking booking) {
        Set<ConstraintViolation<Booking>> violations = validator.validate(booking);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(ENTITY, violations);
        }

        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Long bookingId, Booking request) {
        Set<ConstraintViolation<Booking>> violations = validator.validate(request);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(ENTITY, violations);
        }

        return bookingRepository.findById(bookingId).map(booking ->
                        bookingRepository.save(booking.withUserId(request.getUserId())
                                .withVehicleId(request.getVehicleId())
                                .withBookingDate(request.getBookingDate())
                                .withReturnDate(request.getReturnDate())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, bookingId));
    }

    @Override
    public ResponseEntity<?> deleteBooking(Long bookingId) {
        return bookingRepository.findById(bookingId).map(booking -> {
            bookingRepository.delete(booking);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, bookingId));
    }

    @Override
    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow(() ->
                new ResourceNotFoundException(ENTITY, bookingId));
    }
}