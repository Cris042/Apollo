package org.apollo.core.port.in;

import java.time.LocalDateTime;

public interface ICompleteRoutineUseCase {
    double completeRoutine(Long id,LocalDateTime startTime, LocalDateTime endTime);
}