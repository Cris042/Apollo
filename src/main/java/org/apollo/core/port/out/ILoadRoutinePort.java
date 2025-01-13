package org.apollo.core.port.out;

import org.apollo.core.domain.models.Routine;

import java.util.List;

public interface ILoadRoutinePort {
    Routine loadRoutineByName(Long id);

    Routine loadRoutineByName(String name, String userId);

    List<Routine> loadAllRoutines(String userId);
}