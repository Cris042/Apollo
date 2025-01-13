package org.apollo.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apollo.domain.entities.Routine;
import org.apollo.application.port.in.service.IUpdateRoutineServicePort;
import org.apollo.application.port.out.persistence.ILoadRoutinePersistencePort;
import org.apollo.application.port.out.persistence.ISavePersistenceRoutinePort;

@ApplicationScoped
public class UpdateRoutineService implements IUpdateRoutineServicePort {

    @Inject
    private ILoadRoutinePersistencePort loadRoutinePort;

    @Inject
    private ISavePersistenceRoutinePort saveRoutinePort;

    @Override
    @Transactional
    public Routine updateRoutine(Long id, Routine updatedRoutine) {
        Routine existingRoutine = loadRoutinePort.loadRoutineById(id);

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
