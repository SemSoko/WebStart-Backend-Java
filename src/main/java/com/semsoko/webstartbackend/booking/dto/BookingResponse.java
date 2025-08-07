package com.semsoko.webstartbackend.booking.dto;

import java.time.LocalDateTime;

public class BookingResponse{
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
        return id;
    }

    public LocalDateTime getStartTime(){
        return this.startTime;
    }

    public LocalDateTime getEndTime(){
        return this.endTime;
    }

    public Long getUserId(){
        return this.userId;
    }

    public Long getResourceId(){
        return this.resourceId;
    }

    public boolean isCanceled(){
        return this.canceled;
    }

    public void setId(Long id){
        this.id = id;
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

    public void setResourceId(Long resourceId){
        this.resourceId = resourceId;
    }

    public void setCanceled(boolean canceled){
        this.canceled = canceled;
    }
}
