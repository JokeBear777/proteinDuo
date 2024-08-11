package com.proteinduo.domain.memberManage.dto;

import lombok.*;

/**
 * packageName    : com.proteinduo.domain.memberManage.dto
 * fileName       : memberInfoRequest
 * author         : 82102
 * date           : 2024-08-06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-06        82102       최초 생성
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberInfoRequest {

    private String username;

    private String email;

    private String gender;

    private Integer height;

    private Integer weight;

    private Integer muscleMass;

    private Integer bodyFat;

    private Integer bmi;

    private Integer bodyFatPercentage;


}
