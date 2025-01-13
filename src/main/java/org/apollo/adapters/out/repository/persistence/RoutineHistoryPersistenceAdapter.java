package org.apollo.adapters.out.repository.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apollo.adapters.out.repository.persistence.jpa.JpaRoutineHistoryRepository;
import org.apollo.domain.entities.RoutineHistory;
import org.apollo.application.port.out.persistence.IRoutineHistoryPersistencePort;

@ApplicationScoped
public class RoutineHistoryPersistenceAdapter implements IRoutineHistoryPersistencePort {

    @Inject
    JpaRoutineHistoryRepository jpaRoutineHistoryRepository;

    @Override
    public RoutineHistory saveRoutineHistory(RoutineHistory routineHistory) {
        jpaRoutineHistoryRepository.persist(routineHistory);
        return routineHistory;
    }
}
