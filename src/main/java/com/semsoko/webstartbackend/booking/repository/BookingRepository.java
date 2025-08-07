package com.semsoko.webstartbackend.booking.repository;

import com.semsoko.webstartbackend.booking.model.BookingEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long>{
    /**
     * Erweiterbar z.B. mit findByUserId(...) etc
     */

}
