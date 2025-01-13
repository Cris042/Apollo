package org.apollo.core.port.in;

import org.apollo.core.domain.models.Routine;

public interface IUpdateRoutineUseCase {
    Routine updateRoutine(Long id, Routine routine);
}