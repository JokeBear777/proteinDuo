package com.proteinduo.domain.exerciseManage.service;


import com.proteinduo.domain.exerciseManage.dto.AddExerciseRecordDto;
import com.proteinduo.domain.exerciseManage.dto.GetExerciseRecordInfoDto;
import com.proteinduo.domain.exerciseManage.dto.UpdateExerciseRecordDto;
import com.proteinduo.domain.exerciseManage.entity.Exercise;
import com.proteinduo.domain.exerciseManage.entity.ExerciseRecord;
import com.proteinduo.domain.exerciseManage.repository.ExerciseRecordRepository;
import com.proteinduo.domain.exerciseManage.repository.ExerciseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseRecordService {

    private final ExerciseRecordRepository exerciseRecordRepository;
    private final ExerciseRepository exerciseRepository;

    public ExerciseRecordService(ExerciseRecordRepository exerciseRecordRepository,
                                 ExerciseRepository exerciseRepository) {
        this.exerciseRecordRepository = exerciseRecordRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Transactional(readOnly = true)
    public List<GetExerciseRecordInfoDto> getExerciseRecordsInfo(Long exerciseId) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new IllegalArgumentException("Exercise not found"));
        List<GetExerciseRecordInfoDto> getExerciseRecordInfoDtoList= exercise.getExerciseRecords().stream()
                .map(record -> new GetExerciseRecordInfoDto(record.getWeight(), record.getReps(), record.getRestTime(), record.getCreatedAt()))
                .collect(Collectors.toList());

        return getExerciseRecordInfoDtoList;
    }

    @Transactional(readOnly = true)
    public GetExerciseRecordInfoDto getExerciseRecordInfo(Long exerciseRecordId) {
        ExerciseRecord exerciseRecord = exerciseRecordRepository.findById(exerciseRecordId)
                .orElseThrow(()->new IllegalArgumentException("ExerciseRecord not found"));

        GetExerciseRecordInfoDto getExerciseRecordInfoDto = GetExerciseRecordInfoDto.builder()
                .weight(exerciseRecord.getWeight())
                .reps(exerciseRecord.getReps())
                .restTime(exerciseRecord.getRestTime())
                .createdAt(exerciseRecord.getCreatedAt())
                .build();

        return getExerciseRecordInfoDto;
    }


    @Transactional
    public void createExerciseRecord(Long exerciseId, AddExerciseRecordDto AddExerciseRecordDto) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(()->new IllegalArgumentException("Exercise not found"));

        ExerciseRecord exerciseRecord = ExerciseRecord.builder()
                .weight(AddExerciseRecordDto.getWeight())
                .reps(AddExerciseRecordDto.getReps())
                .restTime(AddExerciseRecordDto.getRestTime())
                .build();
        exercise.getExerciseRecords().add(exerciseRecord);


        exerciseRepository.save(exercise);
    }

    /**
    @Transactional
    public GetExerciseRecordInfoDto getBestExerciseRecordInfo(Long exerciseId) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new IllegalArgumentException("Exercise not found"));

        List<GetExerciseRecordInfoDto> getExerciseRecordInfoDtoList= exercise.getExerciseRecords().stream()
                .map(record -> new GetExerciseRecordInfoDto(record.getWeight(), record.getReps(), record.getRestTime(), record.getCreatedAt()))
                .collect(Collectors.toList());



    }
    **/
    @Transactional
    public void updateExerciseRecord(Long exerciseRecordId, UpdateExerciseRecordDto updateExerciseRecordDto) {
        ExerciseRecord exerciseRecord = exerciseRecordRepository.findById(exerciseRecordId)
                .orElseThrow(() -> new IllegalArgumentException("ExerciseRecord not found"));

        exerciseRecord.setWeight(updateExerciseRecordDto.getWeight());
        exerciseRecord.setReps(updateExerciseRecordDto.getReps());
        exerciseRecord.setRestTime(updateExerciseRecordDto.getRestTime());

        exerciseRecordRepository.save(exerciseRecord);
    }

    @Transactional
    public void deleteExerciseRecord(Long exerciseRecordId) {
        exerciseRecordRepository.deleteById(exerciseRecordId);
    }


}
