package org.apollo.core.port.out;

import org.apollo.core.domain.models.RoutineHistory;

public interface IRoutineHistoryPort {
    RoutineHistory saveRoutineHistory(RoutineHistory routineHistory);
}
