package com.sugandrey.FirstRestApp.controllers;

import com.sugandrey.FirstRestApp.dto.MeasurementDTO;
import com.sugandrey.FirstRestApp.models.Measurement;
import com.sugandrey.FirstRestApp.services.MeasurementService;
import com.sugandrey.FirstRestApp.util.MeasurementErrorResponse;
import com.sugandrey.FirstRestApp.util.MeasurementNotCreatedException;
import com.sugandrey.FirstRestApp.util.MeasurementValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;

    @Autowired
    public MeasurementController(final MeasurementService measurementService, final ModelMapper modelMapper,
                                 final MeasurementValidator measurementValidator
    ) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
    }

    @GetMapping
    public List<MeasurementDTO> getMeasurements() {
        return measurementService.findAll().stream().map(this::convertToMeasurementDTO).collect(
                Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public int getRainyDaysCount() {
        return measurementService.getRainyOrNotRainyDaysCount("false");
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid final MeasurementDTO measurementDTO, final BindingResult result) {
        measurementValidator.validate(convertToMeasurement(measurementDTO), result);
        if (result.hasErrors()) {
            final StringBuilder errMsg = new StringBuilder();
            final List<FieldError> errors = result.getFieldErrors();
            for (final FieldError error : errors) {
                errMsg.append(error.getField())
                      .append(" - ")
                      .append(error.getDefaultMessage())
                      .append(";");
                throw new MeasurementNotCreatedException(errMsg.toString());
            }
        }
        measurementService.createMeasurement(convertToMeasurement(measurementDTO) );
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler()
    private ResponseEntity<MeasurementErrorResponse> handleException(final MeasurementNotCreatedException e) {
        final MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                OffsetDateTime.now(ZoneOffset.UTC).toLocalDateTime()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Measurement convertToMeasurement(final MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(final Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
