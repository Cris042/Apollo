package org.apollo.core.domain.models;

import java.time.LocalDateTime;

public class RoutineHistory {
    private Long id;
    private Long routineID;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double volumeLoad;

    public RoutineHistory(Long id, Long routineID, LocalDateTime startTime, LocalDateTime endTime, double volumeLoad) {
        this.id = id;
        this.routineID = routineID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.volumeLoad = volumeLoad;
    }

    public Long getId() { return id; }
    public Long getroutineID() { return routineID; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public double getVolumeLoad() { return volumeLoad; }

    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    public void setVolumeLoad(double volumeLoad) { this.volumeLoad = volumeLoad; }
}
