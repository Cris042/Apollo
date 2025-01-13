package org.apollo.domain.entities;


import jakarta.persistence.*;


import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "routines")
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String name;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "routine_exercises",
            joinColumns = @JoinColumn(name = "routine_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private List<Exercise> exercises;

    public Routine() {}

    public Routine(String name, UUID userId, List<Exercise> exercises) {
        this.name = name;
        this.userId = userId;
        this.exercises = exercises;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public UUID getUserId() { return userId; }
    public List<Exercise> getExercises() { return exercises; }

    public void setId(UUID id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setUserId(UUID userId) { this.userId = userId; }
    public void setExercises(List<Exercise> exercises) { this.exercises = exercises; }
}