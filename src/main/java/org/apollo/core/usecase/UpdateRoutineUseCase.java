package org.apollo.core.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apollo.core.domain.models.Routine;
import org.apollo.core.port.in.IUpdateRoutineUseCase;
import org.apollo.core.port.out.ILoadRoutinePort;
import org.apollo.core.port.out.ISaveRoutinePort;

@ApplicationScoped
public class UpdateRoutineUseCase implements IUpdateRoutineUseCase {

    @Inject
    private ILoadRoutinePort loadRoutinePort;

    @Inject
    private ISaveRoutinePort saveRoutinePort;

    @Override
    @Transactional
    public Routine updateRoutine(Long id, Routine updatedRoutine) {
        Routine existingRoutine = loadRoutinePort.loadRoutineByName(id);

        if (existingRoutine == null) {
            throw new IllegalArgumentException("Routine not found.");
        }

        if (!existingRoutine.getUserId().equals(updatedRoutine.getUserId())) {
            throw new IllegalArgumentException("Unauthorized to update this routine.");
        }

        existingRoutine.setName(updatedRoutine.getName());
        existingRoutine.setExercises(updatedRoutine.getExercises());

        return saveRoutinePort.saveRoutine(existingRoutine);
    }
}
