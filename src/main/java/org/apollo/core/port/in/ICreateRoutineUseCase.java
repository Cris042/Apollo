package org.apollo.core.port.in;

import org.apollo.core.domain.models.Routine;

public interface ICreateRoutineUseCase {
    Routine createRoutine(Routine routine);
}