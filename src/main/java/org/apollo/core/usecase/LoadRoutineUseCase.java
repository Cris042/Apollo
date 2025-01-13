package org.apollo.core.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apollo.core.domain.models.Routine;
import org.apollo.core.port.in.ILoadRoutineUseCase;
import org.apollo.core.port.out.ILoadRoutinePort;

import java.util.List;

@ApplicationScoped
public class LoadRoutineUseCase  implements ILoadRoutineUseCase {

    @Inject
    private ILoadRoutinePort loadRoutinePort;

    @Override
    public Routine loadRoutineByName(Long id) {
        return loadRoutinePort.loadRoutineByName(id);
    }

    @Override
    public Routine loadRoutineByName(String name, String userId) {
        return loadRoutinePort.loadRoutineByName(name,userId);
    }

    @Override
    public List<Routine> loadAllRoutines(String userId) {
        return loadRoutinePort.loadAllRoutines(userId);
    }
}
