package org.apollo.core.domain.models;


import java.time.LocalDateTime;
import java.util.List;

public class Routine {
    private Long id;
    private String name;
    private String userId;
    private List<Exercise> exercises;

    public Routine(Long id, String name, String userId, List<Exercise> exercises, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.exercises = exercises;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getUserId() { return userId; }
    public List<Exercise> getExercises() { return exercises; }

    public void setName(String name) {
        this.name = name;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

}