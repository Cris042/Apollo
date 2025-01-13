package org.apollo.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apollo.domain.entities.Routine;
import org.apollo.application.port.in.IUpdateRoutineServicePort;
import org.apollo.application.port.out.ILoadRoutinePort;
import org.apollo.application.port.out.ISaveRoutinePort;

@ApplicationScoped
public class UpdateRoutineService implements IUpdateRoutineServicePort {

    @Inject
    private ILoadRoutinePort loadRoutinePort;

    @Inject
    private ISaveRoutinePort saveRoutinePort;

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
