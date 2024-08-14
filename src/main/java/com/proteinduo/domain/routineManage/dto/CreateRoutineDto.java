package com.proteinduo.domain.routineManage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * packageName    : com.proteinduo.domain.routineManage.dto
 * fileName       : CreateRoutineDto
 * author         : 82102
 * date           : 2024-08-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-14        82102       최초 생성
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoutineDto {
    private String routineName;

    private LocalDate createdAt;

    private Integer perWeek;

}
