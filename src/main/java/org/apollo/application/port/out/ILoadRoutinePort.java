package org.apollo.application.port.out;

import org.apollo.domain.entities.Routine;

import java.util.List;
import java.util.UUID;

public interface ILoadRoutinePort {
    Routine loadRoutineById(Long id);

    Routine loadRoutineById(String name, String userId);

    List<Routine> loadAllRoutines( UUID userId);
}