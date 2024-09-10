package com.proteinduo.domain.routineManage.service;


import com.proteinduo.domain.routineManage.entity.Routine;
import com.proteinduo.domain.routineManage.repository.RoutineRepository;
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
        String currentUserName = principal.getName();
        Optional<Routine> routine = routineRepository.findById(routineId);

        return routine.map(r -> r.getMember().getMemberId().equals(currentUserName))
                .orElse(false);


    }


}
