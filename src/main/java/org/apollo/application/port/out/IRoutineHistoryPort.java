package org.apollo.application.port.out;

import org.apollo.domain.entities.RoutineHistory;

public interface IRoutineHistoryPort {
    RoutineHistory saveRoutineHistory(RoutineHistory routineHistory);
}
