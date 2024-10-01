package com.proteinduo.domain.exerciseManage.service;


import com.proteinduo.domain.exerciseManage.dto.*;
import com.proteinduo.domain.exerciseManage.entity.Exercise;
import com.proteinduo.domain.exerciseManage.repository.ExerciseRepository;
import com.proteinduo.domain.routineManage.entity.Routine;
import com.proteinduo.domain.routineManage.repository.RoutineRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final RoutineRepository routineRepository;

    public ExerciseService(ExerciseRepository exerciseRepository,
                           RoutineRepository routineRepository) {
        this.exerciseRepository = exerciseRepository;
        this.routineRepository = routineRepository;
    }

    @Transactional
    public void createExercise(AddExerciseDto addExerciseDto, Integer routineId) {
        Routine routine = routineRepository.findByRoutineId(routineId)
                .orElseThrow(()-> new IllegalArgumentException("Routine not found"));

        Exercise exercise = Exercise.builder()
                .exerciseType(addExerciseDto.getExerciseType())
                .routineId(routine.getRoutineId())
                .build();

        routine.getExercises().add(exercise);

        exerciseRepository.save(exercise);
    }

    @Transactional(readOnly = true)
    public GetExerciseInfoDto getExerciseByExerciseId(Long exerciseId) {
        // Exercise 정보를 가져오기
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new IllegalArgumentException("Exercise not found"));

        // ExerciseRecord 정보들을 DTO 리스트로 변환
        List<GetExerciseRecordInfoDto> getExerciseRecordInfoDtoList = exercise.getExerciseRecords().stream()
                .map(record -> new GetExerciseRecordInfoDto(record.getWeight(), record.getReps(), record.getRestTime(), record.getCreatedAt()))
                .collect(Collectors.toList());

        // 최종 DTO 빌드
        GetExerciseInfoDto getExerciseInfoDto = GetExerciseInfoDto.builder()
                .exerciseId(exercise.getExerciseId()) // Exercise의 ID 등 추가 정보
                .exerciseType(exercise.getExerciseType()) // 필요에 따라 추가
                .getExerciseRecordInfoDtoList(getExerciseRecordInfoDtoList) // 변환된 ExerciseRecord 정보들
                .build();

        return getExerciseInfoDto;
    }

    @Transactional
    public void updateExercise(Long exerciseId, UpdateExerciseDto updateExerciseDto) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new IllegalArgumentException("Routine not found with id: " + exerciseId));

        exercise.setExerciseType(updateExerciseDto.getExerciseType());
        exerciseRepository.save(exercise);

    }

    @Transactional
    public void deleteExercise(Long exerciseId) {
        exerciseRepository.deleteById(exerciseId);

    }





}
