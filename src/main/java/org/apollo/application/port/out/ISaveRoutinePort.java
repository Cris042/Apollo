package org.apollo.application.port.out;

import org.apollo.domain.entities.Routine;

public interface ISaveRoutinePort {
    Routine saveRoutine(Routine routine);
}