package com.proteinduo.domain.matchingManage.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetMatchingInfoDto {

    private Long matchingId;
    private Long memberId;
    private Long partnerId;


}
