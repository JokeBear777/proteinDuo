package com.proteinduo.domain.matchingManage.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterMatchingDto {
    private String gender;
    private String username;
    private String age;
    private String height;
    private String weight;
    private Integer muscleMass;
    private Integer bodyFat;
    private Integer bmi;
    private Integer bodyFatPercentage;
    //기타 사항은 등록폼에서 받는다


}
