package com.proteinduo.domain.exerciseManage.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetExerciseInfoDto {
    private Long exerciseId;
    private ExerciseType exerciseType;
    private List<GetExerciseRecordInfoDto> getExerciseRecordInfoDtoList;

}
