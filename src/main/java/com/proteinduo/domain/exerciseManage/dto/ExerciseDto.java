package com.proteinduo.domain.exerciseManage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * packageName    : com.proteinduo.domain.exerciseManage.dto
 * fileName       : ExerciseDto
 * author         : 82102
 * date           : 2024-08-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-12        82102       최초 생성
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDto {
    private Integer exerciseId;
    private String exerciseName;
    private Integer weight;
    private Integer reps;
    private Integer sets;
    private Integer restTime;
    private Integer cycle;
    private LocalDate createdAt;

    // Getters and Setters
}
