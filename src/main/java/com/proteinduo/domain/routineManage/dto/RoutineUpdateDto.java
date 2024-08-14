package com.proteinduo.domain.routineManage.dto;

import com.proteinduo.domain.exerciseManage.dto.ExerciseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * packageName    : com.proteinduo.domain.routineManage.dto
 * fileName       : RoutineExercisesUpdateDto
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
public class RoutineUpdateDto {
    private String routineName;

    private LocalDate localDate;

    private Integer perWeek;

}
