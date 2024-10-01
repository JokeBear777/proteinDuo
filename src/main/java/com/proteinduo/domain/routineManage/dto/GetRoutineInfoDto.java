package com.proteinduo.domain.routineManage.dto;

import com.proteinduo.domain.exerciseManage.dto.GetExerciseSimpleInfoDto;
import com.proteinduo.domain.exerciseManage.entity.Exercise;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetRoutineInfoDto {
    private Long routineId;
    private String routineName;
    private LocalDate createdAt;
    private Integer perWeek;
    private List<GetExerciseSimpleInfoDto> exerciseList;
}
