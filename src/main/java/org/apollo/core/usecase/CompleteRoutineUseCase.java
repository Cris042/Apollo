package org.apollo.core.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apollo.core.domain.models.Routine;
import org.apollo.core.domain.models.RoutineHistory;
import org.apollo.core.port.in.ICompleteRoutineUseCase;
import org.apollo.core.port.out.ILoadRoutinePort;
import org.apollo.core.port.out.IRoutineHistoryPort;
import org.apollo.core.port.out.ISaveRoutinePort;

import java.time.LocalDateTime;

@ApplicationScoped
public class CompleteRoutineUseCase implements ICompleteRoutineUseCase {

    @Inject
    private ILoadRoutinePort loadRoutinePort;

    @Inject
    private ISaveRoutinePort saveRoutinePort;

    @Inject
    private IRoutineHistoryPort saveRoutineHistoryPort;

    @Override
    @Transactional
    public double completeRoutine(Long routineId,  LocalDateTime startTime, LocalDateTime endTime) {
        Routine routine = loadRoutinePort.loadRoutineByName(routineId);
        if (routine == null) {
            throw new IllegalArgumentException("Routine not found with id: " + routineId);
        }

        double volumeLoad = routine.getExercises().stream()
                .mapToDouble(ex -> ex.getLoad() * ex.getRepetitions() * ex.getSeries())
                .sum();


        saveRoutinePort.saveRoutine(routine);

        RoutineHistory history = new RoutineHistory(
                null,
                routine.getId(),
                startTime,
                endTime,
                volumeLoad
        );

        saveRoutineHistoryPort.saveRoutineHistory(history);

        return volumeLoad;
    }
}
