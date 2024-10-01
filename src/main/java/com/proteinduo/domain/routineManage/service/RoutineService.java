package com.proteinduo.domain.routineManage.service;

import com.proteinduo.domain.exerciseManage.dto.GetExerciseSimpleInfoDto;
import com.proteinduo.domain.exerciseManage.entity.Exercise;
import com.proteinduo.domain.exerciseManage.repository.ExerciseRepository;
import com.proteinduo.domain.memberManage.entity.Member;
import com.proteinduo.domain.memberManage.repository.MemberRepository;
import com.proteinduo.domain.routineManage.dto.CreateRoutineDto;
import com.proteinduo.domain.routineManage.dto.GetRoutineInfoDto;
import com.proteinduo.domain.routineManage.dto.GetRoutinesInfoDto;
import com.proteinduo.domain.routineManage.dto.RoutineUpdateDto;
import com.proteinduo.domain.routineManage.entity.Routine;
import com.proteinduo.domain.routineManage.repository.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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



    @Transactional(readOnly = true)
    public List<GetRoutinesInfoDto> getRoutinesByMemberId(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Member not found"));
        List<Routine> routines = member.getRoutines();

        // Stream API와 생성자를 사용하여 DTO로 변환
        return routines.stream()
                .map(routine -> new GetRoutinesInfoDto(routine.getRoutineId(), routine.getRoutineName()))
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public GetRoutineInfoDto getRoutineById(Integer routineId) {
        Optional<Routine> routine = routineRepository.findById(routineId);
        if(routine.isPresent()) {
            List<GetExerciseSimpleInfoDto> getExerciseSimpleInfoDtos = routine.get().getExercises().stream()
                    .map(exercise -> new GetExerciseSimpleInfoDto(exercise.getExerciseId(), exercise.getExerciseType()))
                    .collect(Collectors.toList());
            GetRoutineInfoDto getRoutineInfoDto = GetRoutineInfoDto.builder()
                    .routineId(routine.get().getRoutineId())
                    .routineName(routine.get().getRoutineName())
                    .createdAt(routine.get().getCreatedAt())
                    .perWeek(routine.get().getPerWeek())
                    .exerciseList(getExerciseSimpleInfoDtos)
                    .build();
            return getRoutineInfoDto;
        }
        else {
            throw new IllegalArgumentException("Routine not found for ID: " + routineId);
        }

    }

    @Transactional
    public void createRoutine(Long Id, CreateRoutineDto createRoutineDto) {

        Member member = memberRepository.findById(Id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with ID: "));


        Routine routine = Routine.builder()
                .routineName(createRoutineDto.getRoutineName())
                .createdAt(createRoutineDto.getCreatedAt())
                .perWeek(createRoutineDto.getPerWeek())
                .memberLongId(member.getId())
                .build();

        member.getRoutines().add(routine);
        routineRepository.save(routine);
    }

    @Transactional
    public void updateRoutine(Integer routineId, RoutineUpdateDto updateDto) {

        Routine routine = routineRepository.findByRoutineId(routineId)
                .orElseThrow(() -> new IllegalArgumentException("Routine not found with id: " + routineId));

        routine.setRoutineName(updateDto.getRoutineName());
        routine.setCreatedAt(updateDto.getLocalDate());
        routine.setPerWeek(updateDto.getPerWeek());

        routineRepository.save(routine);
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

    @Transactional
    public List<GetExerciseSimpleInfoDto> getExercisesByRoutineId(Integer routineId) {

        Routine routine = routineRepository.findByRoutineId(routineId)
                .orElseThrow(() -> new IllegalArgumentException("Routine not found with id: " + routineId));

        // Routine에서 Exercises를 DTO로 변환
        List<GetExerciseSimpleInfoDto> getExerciseSimpleInfoDtoList = routine.getExercises().stream()
                .map(exercise -> new GetExerciseSimpleInfoDto(exercise.getExerciseId(), exercise.getExerciseType()))
                .collect(Collectors.toList());

        return getExerciseSimpleInfoDtoList;
    }

}
