package com.proteinduo.domain.routineManage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetRoutinesInfoDto {

    private Long routineId;

    private String routineName;
}
