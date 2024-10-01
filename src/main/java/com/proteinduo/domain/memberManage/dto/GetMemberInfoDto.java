package com.proteinduo.domain.memberManage.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetMemberInfoDto {

    private String username;
    private String email;
    private String gender;
    private Integer age;
    private Integer height;
    private Integer weight;
    private Integer muscleMass;
    private Integer bodyFat;
    private Integer bmi;
    private Integer bodyFatPercentage;



}
