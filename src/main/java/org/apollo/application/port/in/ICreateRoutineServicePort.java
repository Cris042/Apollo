package org.apollo.application.port.in;

import org.apollo.domain.entities.Routine;

public interface ICreateRoutineServicePort {
    Routine createRoutine(Routine routine);
}