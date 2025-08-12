package com.semsoko.webstartbackend.booking.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Long userId;
    private Long resourceId;

    private boolean canceled;

    /**
     * Getter und Setter
     */

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public LocalDateTime getStartTime(){
        return this.startTime;
    }

    public void setStartTime(LocalDateTime startTime){
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime(){
        return this.endTime;
    }

    public void setEndTime(LocalDateTime endTime){
        this.endTime = endTime;
    }

    public Long getUserId(){
        return userId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

    public Long getResourceId(){
        return this.resourceId;
    }

    public void setResourceId(Long resourceId){
        this.resourceId = resourceId;
    }

    public boolean isCanceled(){
        return this.canceled;
    }

    public void setCanceled(boolean canceled){
        this.canceled = canceled;
    }
}
