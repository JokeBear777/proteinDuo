package com.proteinduo.domain.memberManage.controller;

import com.proteinduo.domain.memberManage.dto.MemberInfoRequest;
import com.proteinduo.domain.memberManage.entity.Member;
import com.proteinduo.domain.memberManage.service.MemberSecurityService;
import com.proteinduo.domain.memberManage.service.MemberService;
import com.proteinduo.domain.memberManage.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

@Controller
@RequestMapping("/member/{Id}")
public class MemberController {

    private final MemberService memberService;
    private final UserDetailService userDetailService;
    private final MemberSecurityService memberSecurityService;

    @Autowired
    public MemberController(MemberService memberService,
                            UserDetailService userDetailService,
                            MemberSecurityService memberSecurityService) {
        this.memberService = memberService;
        this.userDetailService = userDetailService;
        this.memberSecurityService = memberSecurityService;
    }

    // 유저 INFO = memberId와 password 제외한 필드
    // 유저 정보 READ
    @GetMapping
    @PreAuthorize("@memberSecurityService.hasAccessToMember(#Id, principal)")
    public ModelAndView getMemberInfo(@PathVariable Long Id) {
        Member member = memberService.getById(Id);
        if (member == null) {
            throw new IllegalArgumentException("Member not found for ID: " + Id);
        }

        ModelAndView mav = new ModelAndView("memberInfo"); // memberInfo.html 뷰로 반환
        mav.addObject("member", member); // 모델에 member 데이터 추가
        return mav;
    }

    // 유저 정보 CREATE || UPDATE
    @PostMapping
    @PreAuthorize("@memberSecurityService.hasAccessToMember(#Id, principal)")
    public String registerMemberInfo(@PathVariable Long Id, @ModelAttribute MemberInfoRequest memberInfoRequest, Model model) {
        memberService.memberInfoSave(Id, memberInfoRequest);

        // 성공적으로 저장한 후, 목록 페이지로 리다이렉트
        return "redirect:/member/" + Id;
    }

    // 유저 정보 DELETE
    @DeleteMapping
    @PreAuthorize("@memberSecurityService.hasAccessToMember(#Id, principal)")
    public String deleteMemberInfo(@PathVariable Long Id) {
        memberService.memberInfoDelete(Id);
        // 삭제 후 목록 페이지로 리다이렉트
        return "redirect:/members";
    }
}