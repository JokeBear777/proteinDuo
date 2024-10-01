package com.proteinduo.domain.exerciseManage.service.security;
import com.proteinduo.domain.exerciseManage.entity.Exercise;
import com.proteinduo.domain.exerciseManage.repository.ExerciseRepository;
import com.proteinduo.domain.exerciseManage.service.ExerciseService;
import com.proteinduo.domain.memberManage.repository.MemberRepository;
import com.proteinduo.domain.routineManage.entity.Routine;
import com.proteinduo.domain.routineManage.repository.RoutineRepository;
import com.proteinduo.domain.routineManage.service.RoutineService;
import com.proteinduo.infrastructure.security.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
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
        Long currentUserId = SecurityUtil.getCurrentUserId();

        Routine routine = routineRepository.findById(routineId)
                .orElseThrow(() -> new IllegalArgumentException("Routine not found for id: " + routineId));
        if (!currentUserId.equals(routine.getMemberLongId())) {
            return false;
        }

        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new IllegalArgumentException("Exercise not found for id: " + exerciseId));
        if (!routineId.equals(exercise.getRoutineId())) {
            return false;
        }

        return true;
    }



}
