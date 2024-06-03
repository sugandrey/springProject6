package com.sugandrey.FirstRestApp.util;

import java.time.LocalDateTime;

public class SensorErrorResponse {

    private String message;
    private LocalDateTime timeStamp;

    public SensorErrorResponse(final String message, final LocalDateTime timeStamp) {
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(final LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}