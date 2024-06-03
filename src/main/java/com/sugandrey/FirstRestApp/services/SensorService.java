package com.sugandrey.FirstRestApp.services;

import com.sugandrey.FirstRestApp.models.Sensor;
import com.sugandrey.FirstRestApp.repositories.SensorRepository;
import com.sugandrey.FirstRestApp.util.ExceptionMessagesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sugandrey.FirstRestApp.util.SensorNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(final SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<Sensor> findAll() {
        return new ArrayList<>(sensorRepository.findAll());
    }
    public Sensor findById(final int id) {
        final Optional<Sensor> sensor = sensorRepository.findById(id);
        if (sensor.isEmpty()) {
            throw new SensorNotFoundException(ExceptionMessagesEnum.SENSOR_NOT_FOUND_EXCEPTION.getMessage());
        }
        else {
            return sensor.get();
        }
    }

    @Transactional
    public void saveSensor(final Sensor sensor) {
                sensorRepository.save(sensor);
    }

    @Transactional
    public void updateSensor(final Sensor sensor, final int id) {
        final Optional<Sensor> sensorToBeUpdated = sensorRepository.findById(id).stream().findFirst();
        if (sensorToBeUpdated.isPresent()) {
            sensor.setId(sensorToBeUpdated.get().getId());
            sensorRepository.save(sensor);
        }
        else {
            throw new SensorNotFoundException(ExceptionMessagesEnum.SENSOR_NOT_FOUND_EXCEPTION.getMessage());
        }
    }

    @Transactional
    public void deleteSensor(final int id) {
        final Optional<Sensor> sensorToBeDeleted = sensorRepository.findById(id).stream().findFirst();
        if (sensorToBeDeleted.isPresent()) {
            sensorRepository.deleteById(id);
        }
        else {
            throw new SensorNotFoundException(ExceptionMessagesEnum.SENSOR_NOT_FOUND_EXCEPTION.getMessage());
        }
    }

    public boolean isDuplicateName(final String sensorName) {
        final List<Sensor> sensorList = sensorRepository.findAll();
        System.out.println(sensorName);
        final Optional<Sensor> optionalSensor = sensorList.stream().filter(existedSensor -> existedSensor.getSensorName().equals(sensorName)).findFirst();
        return optionalSensor.isPresent();
    }
}
