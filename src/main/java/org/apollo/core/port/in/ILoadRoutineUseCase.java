package org.apollo.core.port.in;

import org.apollo.core.domain.models.Routine;

import java.util.List;

public interface ILoadRoutineUseCase {
    Routine loadRoutineByName(Long id);

    Routine loadRoutineByName(String name, String userId);

    List<Routine> loadAllRoutines(String userId);
}
