package com.sugandrey.FirstRestApp.services;

import com.sugandrey.FirstRestApp.models.Measurement;
import com.sugandrey.FirstRestApp.models.Sensor;
import com.sugandrey.FirstRestApp.repositories.MeasurementRepository;
import com.sugandrey.FirstRestApp.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public MeasurementService(final MeasurementRepository measurementRepository, final SensorRepository sensorRepository) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
    }

    public List<Measurement> findAll() {
        return new ArrayList<>(measurementRepository.findAll());
    }

    public Measurement findById(final int id) {
        final Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        return optionalMeasurement.orElse(null);
    }

    @Transactional
    public void createMeasurement(final Measurement measurement) {
        final Sensor sensor = sensorRepository.findBySensorName(measurement.getSensor().getSensorName());
            final int id = sensor.getId();
            measurement.getSensor().setId(id);
            enrichMeasurement(measurement);
            measurementRepository.save(measurement);
    }

    public int getRainyOrNotRainyDaysCount(final String raining) {
        final List<Measurement> daysList = measurementRepository.findByRaining(raining);
        return daysList.size();
    }

    private void enrichMeasurement(final Measurement measurement) {
        measurement.setCreatedAt(OffsetDateTime.now(ZoneOffset.UTC).toString());
    }
}
