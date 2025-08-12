package com.semsoko.webstartbackend.booking.service;

import com.semsoko.webstartbackend.booking.dto.NewBookingRequest;
import com.semsoko.webstartbackend.booking.dto.BookingResponse;
import com.semsoko.webstartbackend.booking.mapper.BookingMapper;
import com.semsoko.webstartbackend.booking.model.BookingEntity;
import com.semsoko.webstartbackend.booking.repository.BookingRepository;

import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService{
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public BookingServiceImpl(
            BookingRepository bookingRepository,
            BookingMapper bookingMapper)
    {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }

    @Override
    public BookingResponse createBooking(NewBookingRequest request){
        BookingEntity entity = bookingMapper.toEntity(request);
        BookingEntity saved = bookingRepository.save(entity);

        return bookingMapper.toResponse(saved);
    }
}
