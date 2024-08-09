package com.proteinduo.domain.memberManage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.proteinduo.domain.memberManage.dto
 * fileName       : AddMemberRequest
 * author         : 82102
 * date           : 2024-08-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-05        82102       최초 생성
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddMemberRequest {
    private String memberId;
    private String password;
}

