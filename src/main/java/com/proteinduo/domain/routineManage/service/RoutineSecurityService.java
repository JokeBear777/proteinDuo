package com.proteinduo.domain.routineManage.service;


import com.proteinduo.domain.routineManage.entity.Routine;
import com.proteinduo.domain.routineManage.repository.RoutineRepository;
import com.proteinduo.infrastructure.security.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class RoutineSecurityService {

    private final RoutineRepository routineRepository;

    public RoutineSecurityService(RoutineRepository routineRepository) {
        this.routineRepository = routineRepository;
    }

    public boolean hasAccessToRoutine(Integer routineId, Principal principal) {
        Long currentUserId = SecurityUtil.getCurrentUserId();

        Routine routine = routineRepository.findById(routineId)
                .orElseThrow(()-> new RuntimeException("Routine not found"));

        if (!currentUserId.equals(routine.getMemberLongId())) {
            return false;
        }

        return true;
    }


}
