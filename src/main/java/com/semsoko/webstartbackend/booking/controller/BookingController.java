package com.semsoko.webstartbackend.booking.controller;

import com.semsoko.webstartbackend.booking.dto.NewBookingRequest;
import com.semsoko.webstartbackend.booking.dto.BookingResponse;
import com.semsoko.webstartbackend.booking.service.BookingService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService){
       this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@RequestBody NewBookingRequest request){
        BookingResponse response = bookingService.createBooking(request);

        return ResponseEntity.ok(response);
    }
}
