package com.sugandrey.FirstRestApp.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SensorDTO {

    private String sensorName;


    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(final String sensorName) {
        this.sensorName = sensorName;
    }
}
