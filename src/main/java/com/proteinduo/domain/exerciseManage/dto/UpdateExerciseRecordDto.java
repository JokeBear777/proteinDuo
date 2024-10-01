package com.proteinduo.domain.exerciseManage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateExerciseRecordDto {
    private Double weight;
    private Integer reps;
    private Integer restTime;
}
