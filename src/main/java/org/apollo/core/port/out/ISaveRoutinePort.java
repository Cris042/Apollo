package org.apollo.core.port.out;

import org.apollo.core.domain.models.Routine;

public interface ISaveRoutinePort {
    Routine saveRoutine(Routine routine);
}