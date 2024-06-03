package com.sugandrey.FirstRestApp.util;

public class MeasurementNotCreatedException extends RuntimeException{
    private String message;
    public MeasurementNotCreatedException(final String message) {
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
