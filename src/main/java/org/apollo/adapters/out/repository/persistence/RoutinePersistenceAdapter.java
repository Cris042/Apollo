package org.apollo.adapters.out.repository.persistence;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apollo.adapters.out.repository.persistence.jpa.JpaRoutineRepository;
import org.apollo.domain.entities.Routine;
import org.apollo.application.port.out.persistence.IDeleteRoutinePersistencePort;
import org.apollo.application.port.out.persistence.ILoadRoutinePersistencePort;
import org.apollo.application.port.out.persistence.ISavePersistenceRoutinePort;

import java.util.List;
import java.util.UUID;


@ApplicationScoped
public class RoutinePersistenceAdapter implements ILoadRoutinePersistencePort, ISavePersistenceRoutinePort, IDeleteRoutinePersistencePort {

    @Inject
    JpaRoutineRepository jpaRoutineRepository;

    @Override
    public Routine loadRoutineById(Long id) {
        return jpaRoutineRepository.findById(id);
    }

    @Override
    public Routine loadRoutineById(String name, String userId) {
        return jpaRoutineRepository.findByNameAndUserId(name,userId);
    }

    @Override
    public List<Routine> loadAllRoutines(UUID userId) {
        return jpaRoutineRepository.list("userId", userId);
    }

    @Override
    public Routine saveRoutine(Routine routine) {
        jpaRoutineRepository.persist(routine);
        return routine;
    }

    @Override
    public void deleteRoutine(Long id) {
        jpaRoutineRepository.deleteById(id);
    }

}