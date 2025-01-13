package org.apollo.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "routine_history")
public class RoutineHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private UUID routineID;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double volumeLoad;

    public RoutineHistory() {}

    public RoutineHistory(UUID routineID, LocalDateTime startTime, LocalDateTime endTime, double volumeLoad) {
        this.routineID = routineID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.volumeLoad = volumeLoad;
    }

    public UUID getId() { return id; }
    public UUID getRoutineID() { return routineID; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public double getVolumeLoad() { return volumeLoad; }

    public void setId(UUID id) { this.id = id; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    public void setVolumeLoad(double volumeLoad) { this.volumeLoad = volumeLoad; }
}