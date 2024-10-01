package com.proteinduo.domain.matchingManage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddMemberInfoRequestDto {

    //주에 얼마나(일수)
    private Integer perWeek;

    //운동 목표, ->>매우 중요
    private String goal;
    //다이어트, 벌크업 ...ets

    //운동 경력(개월)
    private Integer athleticBackground;

    //몇분할
    private Integer division;



}
