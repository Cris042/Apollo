package org.apollo.application.port.out.persistence;

import org.apollo.domain.entities.Routine;

public interface ISavePersistenceRoutinePort {
    Routine saveRoutine(Routine routine);
}