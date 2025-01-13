package org.apollo.application.port.in;

import org.apollo.domain.entities.Routine;

public interface IUpdateRoutineServicePort {
    Routine updateRoutine(Long id, Routine routine);
}