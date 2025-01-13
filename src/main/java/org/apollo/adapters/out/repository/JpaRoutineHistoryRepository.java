package org.apollo.adapters.out.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.apollo.domain.entities.RoutineHistory;

@ApplicationScoped
public class JpaRoutineHistoryRepository implements PanacheRepositoryBase<RoutineHistory, Long> {
}
