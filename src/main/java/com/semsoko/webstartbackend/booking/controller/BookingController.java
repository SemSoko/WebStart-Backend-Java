package com.semsoko.webstartbackend.booking.controller;

import com.semsoko.webstartbackend.shared.api.ApiResponse;
import com.semsoko.webstartbackend.shared.api.ApiResponseFactory;
import com.semsoko.webstartbackend.booking.dto.NewBookingRequest;
import com.semsoko.webstartbackend.booking.dto.BookingResponse;
import com.semsoko.webstartbackend.booking.service.BookingService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST-Controller fuer das Booking-Modul.
 *
 * Basisroute: /api/v1/bookings
 *
 * Delegiert Aufrufe an {@link BookingService}
 * Erfolgreiche Antworten werden über {@link ApiResponseFactory} im einheitlichen API-Format zurückgegeben.
 * Fehler werden zentral über den GlobalExceptionHandler verarbeitet.
 */
@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {
    private final BookingService bookingService;
    private final ApiResponseFactory apiResponseFactory;

    /**
     * Konstruktor-basierte Abhängigkeitsinjektion.
     *
     * @param bookingService        Service fuer Buchungslogik
     * @param apiResponseFactory    Factory fuer einheitliche API-Antworten
     */
    public BookingController(
            BookingService bookingService,
            ApiResponseFactory apiResponseFactory
    ){
       this.bookingService = bookingService;
       this.apiResponseFactory = apiResponseFactory;
    }

    /**
     * Erstellt eine neue Buchung auf Basis der Client-Anfrage.
     *
     * @param request Eingabedaten fuer die neue Buchung
     * @return Erfolgsantwort mit erstellter Buchung
     */
    @PostMapping
    public ResponseEntity<ApiResponse<BookingResponse>> createBooking(
            @RequestBody NewBookingRequest request
    ){
        BookingResponse response = bookingService.createBooking(request);
        return ResponseEntity.ok(apiResponseFactory.success(response));
    }
}
