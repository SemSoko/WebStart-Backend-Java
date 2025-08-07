package com.semsoko.webstartbackend.booking.dto;

/**
 * Zeitformat: "2025-08-07T14:00:00"
 */
import java.time.LocalDateTime;

public class NewBookingRequest {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Long userId;
    private Long resourceId;

    /**
     * Getter und Setter
     */

    public LocalDateTime getSTartTime(){
        return this.startTime;
    }

    public LocalDateTime getEndTime(){
        return endTime;
    }

    public Long getUserId(){
        return this.userId;
    }

    public void setStartTime(LocalDateTime startTime){
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime){
        this.endTime = endTime;
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
}
