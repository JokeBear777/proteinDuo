package com.proteinduo.domain.routineManage.service;

import com.proteinduo.domain.exerciseManage.repository.ExerciseRepository;
import com.proteinduo.domain.memberManage.entity.Member;
import com.proteinduo.domain.memberManage.repository.MemberRepository;
import com.proteinduo.domain.routineManage.dto.CreateRoutineDto;
import com.proteinduo.domain.routineManage.dto.RoutineUpdateDto;
import com.proteinduo.domain.routineManage.entity.Routine;
import com.proteinduo.domain.routineManage.repository.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.proteinduo.domain.routineManage.service
 * fileName       : RoutineService
 * author         : 82102
 * date           : 2024-08-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-11        82102       최초 생성
 */

@Service
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final ExerciseRepository exerciseRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public RoutineService(RoutineRepository routineRepository,
                       ExerciseRepository exerciseRepository,
                       MemberRepository memberRepository) {
        this.routineRepository = routineRepository;
        this.exerciseRepository = exerciseRepository;
        this.memberRepository = memberRepository;
    }


    @Transactional
    public Routine createRoutine(Routine routine) {

        return routineRepository.save(routine);
    }

    @Transactional(readOnly = true)
    public List<Routine> getRoutinesByMemberId(String memberId) {
        return routineRepository.findByMember_MemberId(memberId);
    }

    @Transactional
    public Optional<Routine> getRoutineById(Integer routineId) {
        return routineRepository.findByRoutineId(routineId);
    }

    @Transactional
    public Routine createRoutine(String memberId, CreateRoutineDto createRoutineDto) {

        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with ID: " + memberId));

        Routine routine = Routine.builder()
                .member(member)
                .routineName(createRoutineDto.getRoutineName())
                .createdAt(createRoutineDto.getCreatedAt())
                .perWeek(createRoutineDto.getPerWeek())
                .build();

        routineRepository.save(routine);

        return routine;
    }

    @Transactional
    public Routine updateRoutine(Integer routineId, RoutineUpdateDto updateDto) {

        Routine routine = routineRepository.findByRoutineId(routineId)
                .orElseThrow(() -> new IllegalArgumentException("Routine not found with id: " + routineId));

        routine.setRoutineName(updateDto.getRoutineName());
        routine.setCreatedAt(updateDto.getLocalDate());
        routine.setPerWeek(updateDto.getPerWeek());

        return routineRepository.save(routine);
    }


    @Transactional
    public void deleteRoutine(Integer routineId) {

        if(routineRepository.existsByRoutineId(routineId)) {
            routineRepository.deleteByRoutineId(routineId);
        }
        else {
            throw new RuntimeException("Routine not found");
        }



    }

}
