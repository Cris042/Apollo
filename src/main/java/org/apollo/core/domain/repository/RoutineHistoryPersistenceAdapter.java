package org.apollo.core.domain.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apollo.core.domain.models.RoutineHistory;
import org.apollo.core.port.out.IRoutineHistoryPort;

@ApplicationScoped
public class RoutineHistoryPersistenceAdapter implements IRoutineHistoryPort {

    @Inject
    JpaRoutineHistoryRepository jpaRoutineHistoryRepository;

    @Override
    public RoutineHistory saveRoutineHistory(RoutineHistory routineHistory) {
        jpaRoutineHistoryRepository.persist(routineHistory);
        return routineHistory;
    }
}
