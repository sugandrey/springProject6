package com.sugandrey.FirstRestApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "measurement")
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    @NotNull(message = "Значение температуры не должно быть пустым")
    @Min(-100)
    @Max(100)
    private long value;

    @Column(name = "raining")
    @NotNull(message = "Значение влажности не должно быть пустым")
    private String raining;

    @Column(name = "created_at")
    private String createdAt;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    @NotNull(message = "Значение sensor не должно быть пустым")
    private Sensor sensor;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public long getValue() {
        return value;
    }

    public void setValue(final long value) {
        this.value = value;
    }

    public String getRaining() {
        return raining;
    }

    public void setRaining(final String raining) {
        this.raining = raining;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final String createdAt) {
        this.createdAt = createdAt;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(final Sensor sensor) {
        this.sensor = sensor;
    }
}
