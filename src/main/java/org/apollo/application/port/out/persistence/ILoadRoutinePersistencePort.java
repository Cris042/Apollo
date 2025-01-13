package org.apollo.application.port.out.persistence;

import org.apollo.domain.entities.Routine;

import java.util.List;
import java.util.UUID;

public interface ILoadRoutinePersistencePort {
    Routine loadRoutineById(Long id);

    Routine loadRoutineById(String name, String userId);

    List<Routine> loadAllRoutines( UUID userId);
}