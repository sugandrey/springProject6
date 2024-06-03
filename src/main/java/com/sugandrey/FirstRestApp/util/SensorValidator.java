package com.sugandrey.FirstRestApp.util;

import com.sugandrey.FirstRestApp.models.Sensor;
import com.sugandrey.FirstRestApp.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorValidator(final SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        final Sensor sensor = (Sensor) target;
        if (sensor.getSensorName().isEmpty()) {
            errors.rejectValue("sensorName", "", ExceptionMessagesEnum.EMPTY_SENSOR_EXCEPTION.getMessage());
        }
        if (sensor.getSensorName().length() < 3 && !sensor.getSensorName().isEmpty()) {
            errors.rejectValue("sensorName", "", ExceptionMessagesEnum.TOO_LONG_OR_SHORT_SENSOR_NAME.getMessage());
        }
        if (sensor.getSensorName().length() > 30) {
            errors.rejectValue("sensorName", "", ExceptionMessagesEnum.TOO_LONG_OR_SHORT_SENSOR_NAME.getMessage());
        }
        if (sensorService.isDuplicateName(sensor.getSensorName())) {
            errors.rejectValue("sensorName", "", ExceptionMessagesEnum.SENSOR_DUPLICATE_EXCEPTION.getMessage());

        }
    }
}
