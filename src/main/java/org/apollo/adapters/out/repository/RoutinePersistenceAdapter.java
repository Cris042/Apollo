package org.apollo.adapters.out.repository;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apollo.domain.entities.Routine;
import org.apollo.application.port.out.IDeleteRoutinePort;
import org.apollo.application.port.out.ILoadRoutinePort;
import org.apollo.application.port.out.ISaveRoutinePort;

import java.util.List;
import java.util.UUID;


@ApplicationScoped
public class RoutinePersistenceAdapter implements ILoadRoutinePort, ISaveRoutinePort, IDeleteRoutinePort {

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