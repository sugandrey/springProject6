package com.sugandrey.FirstRestApp.util;

public class PersonErrorResponse {

    private String message;
    private long timeStamp;

    public PersonErrorResponse(final String message, final long timeStamp) {
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(final long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
