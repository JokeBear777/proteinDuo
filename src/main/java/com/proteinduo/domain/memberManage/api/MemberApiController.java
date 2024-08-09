package com.proteinduo.domain.memberManage.api;

import com.proteinduo.domain.memberManage.dto.MemberInfoRequest;
import com.proteinduo.domain.memberManage.entity.Member;
import com.proteinduo.domain.memberManage.service.MemberService;
import com.proteinduo.domain.memberManage.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

/**
 * packageName    : com.proteinduo.domain.memberManage.api
 * fileName       : memberAPIController
 * author         : 82102
 * date           : 2024-08-06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-06        82102       최초 생성
 */

@RestController
@RequestMapping("/member/{memberId}")
public class MemberApiController {

    private final MemberService memberService;
    private final UserDetailService userDetailService;

    @Autowired
    public MemberApiController(MemberService memberService, UserDetailService userDetailService) {
        this.memberService = memberService;
        this.userDetailService = userDetailService;
    }


    //유저 INFO = memberId와 password 제외한 필드
    //유저 정보 READ
    @GetMapping
    public ResponseEntity<Member> getMemberInfo(@PathVariable String memberId) {
        try {
            Member member = memberService.getMemberById(memberId);
            return new ResponseEntity<>(member, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 유저 정보 CREATED || UPDATE
    @PostMapping
    public ResponseEntity<Void> registerMemberInfo(@PathVariable String memberId, @RequestBody MemberInfoRequest memberInfoRequest) {

        memberService.memberInfoSave(memberId, memberInfoRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 유저 정보 DELETE
    @DeleteMapping
    public ResponseEntity<Void> deleteMemberInfo(@PathVariable String memberId) {
        memberService.memberInfoDelete(memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}