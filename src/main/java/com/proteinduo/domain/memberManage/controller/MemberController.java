package com.proteinduo.domain.memberManage.controller;

import com.proteinduo.domain.memberManage.dto.GetMemberInfoDto;
import com.proteinduo.domain.memberManage.dto.MemberInfoRequest;
import com.proteinduo.domain.memberManage.service.MemberSecurityService;
import com.proteinduo.domain.memberManage.service.MemberService;
import com.proteinduo.infrastructure.security.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String getMemberInfo(@PathVariable Long Id, Model model) {
        GetMemberInfoDto getMemberInfoDto = memberService.getMemberInfoById(Id);
        model.addAttribute("memberInfoDto", getMemberInfoDto);

        return "memberInfo";
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