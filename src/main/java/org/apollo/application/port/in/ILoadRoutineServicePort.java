package org.apollo.application.port.in;

import org.apollo.domain.entities.Routine;

import java.util.List;
import java.util.UUID;

public interface ILoadRoutineServicePort {
    Routine loadRoutineByName(Long id);

    Routine loadRoutineByName(String name, String userId);

    List<Routine> loadAllRoutines(UUID userId);
}
