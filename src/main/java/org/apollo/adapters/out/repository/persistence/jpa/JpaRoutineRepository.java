package org.apollo.adapters.out.repository.persistence.jpa;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.apollo.domain.entities.Routine;

@ApplicationScoped
public class JpaRoutineRepository implements PanacheRepositoryBase<Routine, Long> {

    public Routine findByNameAndUserId(String name, String userId) {
        return find("name = ?1 and userId = ?2", name, userId).firstResult();
    }
}