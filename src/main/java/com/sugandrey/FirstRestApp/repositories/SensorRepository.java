package com.sugandrey.FirstRestApp.repositories;

import com.sugandrey.FirstRestApp.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    Sensor findBySensorName(String sensorName);

}
