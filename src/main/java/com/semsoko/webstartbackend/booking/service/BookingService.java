package com.semsoko.webstartbackend.booking.service;

import com.semsoko.webstartbackend.booking.dto.NewBookingRequest;
import com.semsoko.webstartbackend.booking.dto.BookingResponse;

public interface BookingService {
    BookingResponse createBooking(NewBookingRequest request);
}
