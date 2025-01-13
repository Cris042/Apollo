package org.apollo.core.domain.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.apollo.core.domain.models.RoutineHistory;

@ApplicationScoped
public class JpaRoutineHistoryRepository implements PanacheRepositoryBase<RoutineHistory, Long> {
}
