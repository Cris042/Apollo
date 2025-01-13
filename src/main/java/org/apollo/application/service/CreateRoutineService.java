package org.apollo.application.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apollo.domain.entities.Routine;
import org.apollo.application.port.in.service.ICreateRoutineServicePort;
import org.apollo.application.port.out.persistence.ILoadRoutinePersistencePort;
import org.apollo.application.port.out.persistence.ISavePersistenceRoutinePort;

@ApplicationScoped
public class CreateRoutineService implements ICreateRoutineServicePort {

    @Inject
    private ILoadRoutinePersistencePort loadRoutinePort;

    @Inject
    private ISavePersistenceRoutinePort saveRoutinePort;

    @Override
    @Transactional
    public Routine createRoutine(Routine routine) {

        boolean routineExists = loadRoutinePort.loadAllRoutines(routine.getUserId()).stream()
                .anyMatch(existingRoutine -> existingRoutine.getName().equalsIgnoreCase(routine.getName()));
        if (routineExists) {
            throw new IllegalArgumentException("A routine with this name already exists for the user.");
        }

        return saveRoutinePort.saveRoutine(routine);
    }
}