package org.apollo.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apollo.domain.entities.Routine;
import org.apollo.application.port.in.service.ILoadRoutineServicePort;
import org.apollo.application.port.out.persistence.ILoadRoutinePersistencePort;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class LoadRoutineService implements ILoadRoutineServicePort {

    @Inject
    private ILoadRoutinePersistencePort loadRoutinePort;

    @Override
    public Routine loadRoutineByName(Long id) {
        return loadRoutinePort.loadRoutineById(id);
    }

    @Override
    public Routine loadRoutineByName(String name, String userId) {
        return loadRoutinePort.loadRoutineById(name,userId);
    }

    @Override
    public List<Routine> loadAllRoutines(UUID userId) {
        return loadRoutinePort.loadAllRoutines(userId);
    }
}
