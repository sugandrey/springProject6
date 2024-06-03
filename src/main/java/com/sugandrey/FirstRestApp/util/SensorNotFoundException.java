package com.sugandrey.FirstRestApp.util;

public class SensorNotFoundException extends RuntimeException{

    private String message;
    public SensorNotFoundException(final String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
