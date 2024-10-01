package com.proteinduo.domain.exerciseManage.dto;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetExerciseRecordInfoDto {
    private Double weight;
    private Integer reps;
    private Integer restTime;
    private Date createdAt;

}
