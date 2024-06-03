package com.sugandrey.FirstRestApp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "sensor")
public class Sensor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "sensor_name")
    @NotNull(message = "Название сенсора не должно быть пустым")
    @Size(min = 2, max = 30, message = "Название сенсора должно быть более 2 и не более 30 символов")
    private String sensorName;
    @OneToMany(mappedBy = "sensor", fetch = FetchType.LAZY)
    private List<Measurement> measurements;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(final String sensorName) {
        this.sensorName = sensorName;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(final List<Measurement> measurements) {
        this.measurements = measurements;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Sensor sensor = (Sensor) o;
        return Objects.equals(sensorName, sensor.sensorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensorName);
    }
}

