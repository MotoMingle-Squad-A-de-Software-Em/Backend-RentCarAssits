package com.motomingle.rentcarassits.iam_management.api;

import com.motomingle.rentcarassits.iam_management.domain.service.BookingService;
import com.motomingle.rentcarassits.iam_management.mapping.BookingMapper;
import com.motomingle.rentcarassits.iam_management.resource.BookingResource;
import com.motomingle.rentcarassits.iam_management.resource.CreateBookingResource;
import com.motomingle.rentcarassits.iam_management.resource.UpdateBookingResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Tag(name = "Bookings", description = "Create, read, update and delete bookings")
@RestController
@RequestMapping(value = "api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    public BookingController(BookingService bookingService, BookingMapper bookingMapper) {
        this.bookingService = bookingService;
        this.bookingMapper = bookingMapper;
    }

    @Operation(summary = "Get all bookings", description = "Get all bookings stored in the database.")
    @ApiResponse(responseCode = "200", description = "Bookings found",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BookingResource.class)))
    @GetMapping
    public Page<BookingResource> getAllBookings(Pageable pageable) {
        return bookingMapper.modelListPage(bookingService.getAllBookings(), pageable);
    }

    @Operation(summary = "Get booking by Id", description = "Get booking by Id.")
    @ApiResponse(responseCode = "200", description = "Booking found",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BookingResource.class)))
    @GetMapping("{bookingId}")
    public BookingResource getBookingById(@PathVariable Long bookingId) {
        return bookingMapper.toResource(bookingService.getBookingById(bookingId));
    }

    @Operation(summary = "Create booking", description = "Create booking in the database.")
    @ApiResponse(responseCode = "201", description = "Booking created",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BookingResource.class)))
    @PostMapping()
    public BookingResource createBooking(@RequestBody CreateBookingResource resource) {
        return bookingMapper.toResource(bookingService.createBooking(bookingMapper.toModel(resource)));
    }

    @Operation(summary = "Update booking", description = "Update booking in the database.")
    @ApiResponse(responseCode = "200", description = "Booking updated",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BookingResource.class)))
    @PutMapping("{bookingId}")
    public BookingResource updateBooking(@PathVariable Long bookingId, @RequestBody UpdateBookingResource resource) {
        return bookingMapper.toResource(bookingService.updateBooking(bookingId, bookingMapper.toModel(resource)));
    }

    @Operation(summary = "Delete booking", description = "Delete booking from the database.")
    @ApiResponse(responseCode = "200", description = "Booking deleted",
            content = @Content(mediaType = "application/json"))
    @DeleteMapping("{bookingId}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long bookingId) {
        return bookingService.deleteBooking(bookingId);
    }
}