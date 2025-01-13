package org.apollo.core.domain.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.apollo.core.domain.models.Routine;

@ApplicationScoped
public class JpaRoutineRepository implements PanacheRepositoryBase<Routine, Long> {

    public Routine findByNameAndUserId(String name, String userId) {
        return find("name = ?1 and userId = ?2", name, userId).firstResult();
    }
}