package org.apollo.core.usecase;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apollo.core.domain.models.Routine;
import org.apollo.core.port.in.ICreateRoutineUseCase;
import org.apollo.core.port.out.ILoadRoutinePort;
import org.apollo.core.port.out.ISaveRoutinePort;

@ApplicationScoped
public class CreateRoutineUseCase implements ICreateRoutineUseCase {

    @Inject
    private ILoadRoutinePort loadRoutinePort;

    @Inject
    private ISaveRoutinePort saveRoutinePort;

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