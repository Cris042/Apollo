package org.apollo.core.domain.repository;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apollo.core.domain.models.Routine;
import org.apollo.core.port.out.IDeleteRoutinePort;
import org.apollo.core.port.out.ILoadRoutinePort;
import org.apollo.core.port.out.ISaveRoutinePort;

import java.util.List;


@ApplicationScoped
public class RoutinePersistenceAdapter implements ILoadRoutinePort, ISaveRoutinePort, IDeleteRoutinePort {

    @Inject
    JpaRoutineRepository jpaRoutineRepository;

    @Override
    public Routine loadRoutineByName(Long id) {
        return jpaRoutineRepository.findById(id);
    }

    @Override
    public Routine loadRoutineByName(String name, String userId) {
        return jpaRoutineRepository.findByNameAndUserId(name,userId);
    }

    @Override
    public List<Routine> loadAllRoutines(String userId) {
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