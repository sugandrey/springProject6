package com.sugandrey.FirstRestApp.dto;

import com.sugandrey.FirstRestApp.models.Sensor;

public class MeasurementDTO {

    private long value;

    private String raining;

    private SensorDTO sensor;

    public long getValue() {
        return value;
    }

    public void setValue(final long value) {
        this.value = value;
    }

    public String getRaining() {
        return raining;
    }

    public void setRaining(final String raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(final SensorDTO sensor) {
        this.sensor = sensor;
    }
}
