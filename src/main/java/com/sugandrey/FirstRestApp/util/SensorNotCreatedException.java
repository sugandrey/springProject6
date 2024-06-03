package com.sugandrey.FirstRestApp.util;

public class SensorNotCreatedException extends RuntimeException{

    private String message;
    public SensorNotCreatedException(final String message) {
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
