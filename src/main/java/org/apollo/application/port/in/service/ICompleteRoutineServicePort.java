package org.apollo.application.port.in.service;

import java.time.LocalDateTime;

public interface ICompleteRoutineServicePort {
    double completeRoutine(Long id,LocalDateTime startTime, LocalDateTime endTime);
}