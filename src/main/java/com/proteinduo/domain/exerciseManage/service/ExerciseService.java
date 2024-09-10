package com.proteinduo.domain.exerciseManage.service;


import com.proteinduo.domain.exerciseManage.dto.AddExerciseDto;
import com.proteinduo.domain.exerciseManage.dto.UpdateExerciseDto;
import com.proteinduo.domain.exerciseManage.entity.Exercise;
import com.proteinduo.domain.exerciseManage.repository.ExerciseRepository;
import com.proteinduo.domain.routineManage.entity.Routine;
import com.proteinduo.domain.routineManage.repository.RoutineRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public Exercise createExercise(AddExerciseDto addExerciseDto, Integer routineId) {
        Routine routine = routineRepository.findByRoutineId(routineId)
                .orElseThrow(()-> new IllegalArgumentException("Routine not found"));

        Exercise exercise = Exercise.builder()
                .routine(routine)
                .exerciseType(addExerciseDto.getExerciseType())
                .build();

        return exerciseRepository.save(exercise);
    }

    @Transactional(readOnly = true)
    public Optional<Exercise> getExerciseByExerciseId(Long exerciseId) {
        return exerciseRepository.findById(exerciseId);
    }

    @Transactional
    public Exercise updateExercise(Long exerciseId, UpdateExerciseDto updateExerciseDto) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new IllegalArgumentException("Routine not found with id: " + exerciseId));

        exercise.setExerciseType(updateExerciseDto.getExerciseType());
        return exerciseRepository.save(exercise);

    }

    @Transactional
    public void deleteExercise(Long exerciseId) {
        exerciseRepository.deleteById(exerciseId);

    }





}
