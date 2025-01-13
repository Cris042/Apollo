package org.apollo.adapters.out.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apollo.domain.entities.RoutineHistory;
import org.apollo.application.port.out.IRoutineHistoryPort;

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
