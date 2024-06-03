package com.sugandrey.FirstRestApp.util;

import com.sugandrey.FirstRestApp.models.Measurement;
import com.sugandrey.FirstRestApp.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(final SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(final Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        final Measurement measurement = (Measurement) target;
        final boolean isCorrectRainingField = measurement.getRaining().equalsIgnoreCase("true")
                                              || measurement.getRaining().equalsIgnoreCase("false");
        if (!sensorService.isDuplicateName(measurement.getSensor().getSensorName()) && !measurement.getSensor().getSensorName().isEmpty()) {
            errors.rejectValue("sensor", "", ExceptionMessagesEnum.NEED_REGISTER_SENSOR.getMessage());
        }
         if (Long.toString(measurement.getValue()).isEmpty()) {
             errors.rejectValue("value", "", ExceptionMessagesEnum.NON_NULL_TEMPERATURE.getMessage());
         }
         if (measurement.getValue() < -100 || measurement.getValue() > 100) {
             errors.rejectValue("value", "", ExceptionMessagesEnum.OUT_OF_BOUND_TEMPERATURE_RANGE.getMessage());
         }
         if (measurement.getSensor().getSensorName().isEmpty()) {
             errors.rejectValue("value", "", ExceptionMessagesEnum.EMPTY_SENSOR_EXCEPTION.getMessage());
         }
        if (measurement.getRaining().isEmpty() || !isCorrectRainingField) {
            errors.rejectValue("raining", "", ExceptionMessagesEnum.EMPTY_RAINING_FIELD.getMessage());
        }
    }
}
