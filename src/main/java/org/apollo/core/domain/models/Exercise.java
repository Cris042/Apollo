package org.apollo.core.domain.models;

public class Exercise {
    private String name;
    private int repetitions;
    private double load;
    private int restTime;
    private int series;

    public Exercise(String name, int repetitions, double load, int restTime, int series) {
        this.name = name;
        this.repetitions = repetitions;
        this.restTime = restTime;
        this.series = series;
    }

    public String getName() { return name; }
    public int getRepetitions() { return repetitions; }
    public double getLoad() { return load; }
    public int getRestTime() { return restTime; }
    public int getSeries() { return series; }
}
