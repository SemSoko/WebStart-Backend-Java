package com.semsoko.webstartbackend.booking.mapper;

import com.semsoko.webstartbackend.booking.dto.NewBookingRequest;
import com.semsoko.webstartbackend.booking.dto.BookingResponse;
import com.semsoko.webstartbackend.booking.model.BookingEntity;

import org.springframework.stereotype.Component;

@Component
public class BookingMapperImpl implements BookingMapper{
    @Override
    public BookingEntity toEntity(NewBookingRequest request){
        BookingEntity entity = new BookingEntity();
        entity.setStartTime(request.getSTartTime());
        entity.setEndTime(request.getEndTime());
        entity.setUserId(request.getUserId());
        entity.setResourceId(request.getResourceId());
        entity.setCanceled(false);

        return entity;
    }

    @Override
    public BookingResponse toResponse(BookingEntity entity){
        BookingResponse response = new BookingResponse();
        response.setId(entity.getId());
        response.setStartTime(entity.getStartTime());
        response.setEndTime(entity.getEndTime());
        response.setUserId(entity.getUserId());
        response.setResourceId(entity.getResourceId());
        response.setCanceled(entity.isCanceled());

        return response;
    }
}
