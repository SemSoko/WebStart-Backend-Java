package com.semsoko.webstartbackend.booking.mapper;

import com.semsoko.webstartbackend.booking.dto.NewBookingRequest;
import com.semsoko.webstartbackend.booking.dto.BookingResponse;
import com.semsoko.webstartbackend.booking.model.BookingEntity;

public interface BookingMapper{
    BookingEntity toEntity(NewBookingRequest request);
    BookingResponse toResponse(BookingEntity entity);
}
