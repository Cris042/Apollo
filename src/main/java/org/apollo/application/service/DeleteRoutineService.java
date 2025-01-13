package org.apollo.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apollo.domain.entities.Routine;
import org.apollo.application.port.in.service.IDeleteRoutineServicePort;
import org.apollo.application.port.out.persistence.IDeleteRoutinePersistencePort;
import org.apollo.application.port.out.persistence.ILoadRoutinePersistencePort;

@ApplicationScoped
public class DeleteRoutineService implements IDeleteRoutineServicePort {

    @Inject
    private ILoadRoutinePersistencePort loadRoutinePort;

    @Inject
    private IDeleteRoutinePersistencePort deleteRoutinePort;

    @Override
    @Transactional
    public void deleteRoutine(Long id, String userId) {
        Routine routine = loadRoutinePort.loadRoutineById(id);

        if (routine == null) {
            throw new IllegalArgumentException("Routine not found.");
        }

        if (!routine.getUserId().equals(userId)) {
            throw new IllegalArgumentException("Unauthorized to delete this routine.");
        }

        deleteRoutinePort.deleteRoutine(id);
    }
}
