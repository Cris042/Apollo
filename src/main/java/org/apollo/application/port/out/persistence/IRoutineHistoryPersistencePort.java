package org.apollo.application.port.out.persistence;

import org.apollo.domain.entities.RoutineHistory;

public interface IRoutineHistoryPersistencePort {
    RoutineHistory saveRoutineHistory(RoutineHistory routineHistory);
}
