package org.apollo.core.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apollo.core.domain.models.Routine;
import org.apollo.core.port.in.IDeleteRoutineUseCase;
import org.apollo.core.port.out.IDeleteRoutinePort;
import org.apollo.core.port.out.ILoadRoutinePort;

@ApplicationScoped
public class DeleteRoutineUseCase implements IDeleteRoutineUseCase {

    @Inject
    private ILoadRoutinePort loadRoutinePort;

    @Inject
    private IDeleteRoutinePort deleteRoutinePort;

    @Override
    @Transactional
    public void deleteRoutine(Long id, String userId) {
        Routine routine = loadRoutinePort.loadRoutineByName(id);

        if (routine == null) {
            throw new IllegalArgumentException("Routine not found.");
        }

        if (!routine.getUserId().equals(userId)) {
            throw new IllegalArgumentException("Unauthorized to delete this routine.");
        }

        deleteRoutinePort.deleteRoutine(id);
    }
}
