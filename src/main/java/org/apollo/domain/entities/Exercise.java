package org.apollo.domain.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String name;
    private int repetitions;
    private double load;

    @Column(name = "rest_time", nullable = false)
    private int restTime;

    private int series;

    public Exercise() {
    }

    public Exercise(String name, int repetitions, double load, int restTime, int series) {
        this.name = name;
        this.repetitions = repetitions;
        this.load = load;
        this.restTime = restTime;
        this.series = series;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public double getLoad() {
        return load;
    }

    public int getRestTime() {
        return restTime;
    }

    public int getSeries() {
        return series;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public void setLoad(double load) {
        this.load = load;
    }

    public void setRestTime(int restTime) {
        this.restTime = restTime;
    }

    public void setSeries(int series) {
        this.series = series;
    }
}