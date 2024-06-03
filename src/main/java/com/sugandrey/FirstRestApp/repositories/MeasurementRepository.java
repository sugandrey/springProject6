package com.sugandrey.FirstRestApp.repositories;

import com.sugandrey.FirstRestApp.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

        List<Measurement> findByRaining(String raining);
}
