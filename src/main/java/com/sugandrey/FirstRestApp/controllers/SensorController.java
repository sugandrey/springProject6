package com.sugandrey.FirstRestApp.controllers;

import com.sugandrey.FirstRestApp.dto.SensorDTO;
import com.sugandrey.FirstRestApp.models.Sensor;
import com.sugandrey.FirstRestApp.services.SensorService;
import com.sugandrey.FirstRestApp.util.ExceptionMessagesEnum;
import com.sugandrey.FirstRestApp.util.PersonErrorResponse;
import com.sugandrey.FirstRestApp.util.SensorErrorResponse;
import com.sugandrey.FirstRestApp.util.SensorNotCreatedException;
import com.sugandrey.FirstRestApp.util.SensorNotFoundException;
import com.sugandrey.FirstRestApp.util.SensorValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final ModelMapper modelMapper;

    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(final SensorService sensorService, final ModelMapper modelMapper,
                            final SensorValidator sensorValidator
    ) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @GetMapping
    public List<SensorDTO> getSensors() {
        return sensorService.findAll().stream().map(this::convertToSensorDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SensorDTO findSensor(@PathVariable("id") final int id) {
        return convertToSensorDTO(sensorService.findById(id));
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> createSensor(@RequestBody @Valid final SensorDTO sensorDTO, final BindingResult result) {
        sensorValidator.validate(convertToSensor(sensorDTO), result);
        if (result.hasErrors()) {
            final StringBuilder errMsg = new StringBuilder();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                errMsg.append(error.getField())
                      .append(" - ")
                      .append(error.getDefaultMessage())
                      .append(";");
                throw new SensorNotCreatedException(errMsg.toString());
            }
        }
        sensorService.saveSensor(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler()
    private ResponseEntity<SensorErrorResponse> handleException(final SensorNotCreatedException e) {
        final SensorErrorResponse response = new SensorErrorResponse(e.getMessage(), OffsetDateTime.now(ZoneOffset.UTC).toLocalDateTime());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler()
    private ResponseEntity<SensorErrorResponse> handleException(final SensorNotFoundException e) {
        e.setMessage(ExceptionMessagesEnum.SENSOR_NOT_FOUND_EXCEPTION.getMessage());
        final SensorErrorResponse response = new SensorErrorResponse(e.getMessage(), OffsetDateTime.now(ZoneOffset.UTC).toLocalDateTime());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private SensorDTO convertToSensorDTO(final Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }
    private Sensor convertToSensor(final SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
