package org.apollo.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apollo.domain.entities.Routine;
import org.apollo.domain.entities.RoutineHistory;
import org.apollo.application.port.in.service.ICompleteRoutineServicePort;
import org.apollo.application.port.out.persistence.ILoadRoutinePersistencePort;
import org.apollo.application.port.out.persistence.IRoutineHistoryPersistencePort;
import org.apollo.application.port.out.persistence.ISavePersistenceRoutinePort;

import java.time.LocalDateTime;

@ApplicationScoped
public class CompleteRoutineService implements ICompleteRoutineServicePort {

    @Inject
    private ILoadRoutinePersistencePort loadRoutinePort;

    @Inject
    private ISavePersistenceRoutinePort saveRoutinePort;

    @Inject
    private IRoutineHistoryPersistencePort saveRoutineHistoryPort;

    @Override
    @Transactional
    public double completeRoutine(Long routineId,  LocalDateTime startTime, LocalDateTime endTime) {
        Routine routine = loadRoutinePort.loadRoutineById(routineId);
        if (routine == null) {
            throw new IllegalArgumentException("Routine not found with id: " + routineId);
        }

        double volumeLoad = routine.getExercises().stream()
                .mapToDouble(ex -> ex.getLoad() * ex.getRepetitions() * ex.getSeries())
                .sum();


        saveRoutinePort.saveRoutine(routine);

        RoutineHistory history = new RoutineHistory(
                routine.getId(),
                startTime,
                endTime,
                volumeLoad
        );

        saveRoutineHistoryPort.saveRoutineHistory(history);

        return volumeLoad;
    }
}
