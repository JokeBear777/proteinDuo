package com.proteinduo.domain.exerciseManage.service.security;
import com.proteinduo.domain.exerciseManage.entity.Exercise;
import com.proteinduo.domain.exerciseManage.repository.ExerciseRepository;
import com.proteinduo.domain.exerciseManage.service.ExerciseService;
import com.proteinduo.domain.routineManage.entity.Routine;
import com.proteinduo.domain.routineManage.repository.RoutineRepository;
import com.proteinduo.domain.routineManage.service.RoutineService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class ExerciseSecurityService {

    private final RoutineRepository routineRepository;
    private final ExerciseRepository exerciseRepository;

    public ExerciseSecurityService(RoutineRepository routineRepository,
                                   ExerciseRepository exerciseRepository) {
        this.routineRepository = routineRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public boolean hasAccessToExerciseAndRoutine(Long exerciseId, Integer routineId, Principal principal) {

        String currentUserName = principal.getName();

        return routineRepository.findById(routineId)
                .filter(routine -> currentUserName.equals((routine.getMember().getMemberId())))
                .flatMap(routine -> exerciseRepository.findById(exerciseId))
                .map(exercise -> exercise.getRoutine().getRoutineId().equals(routineId))
                .orElse(false);
    }


}
