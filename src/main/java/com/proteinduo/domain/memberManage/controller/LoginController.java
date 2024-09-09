package com.proteinduo.domain.memberManage.controller;

import com.proteinduo.domain.memberManage.dto.AddMemberRequest;
import com.proteinduo.domain.memberManage.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * <br>package name   : com.proteinduo.domain.memberManage.api
 * <br>file name      : MemberController
 * <br>date           : 2024-07-28
 * <pre>
 * <span style="color: white;">[description]</span>
 *
 * </pre>
 * <pre>
 * <span style="color: white;">usage:</span>
 * {@code
 *
 * } </pre>
 * <pre>
 * modified log :
 * ====================================================
 * DATE           AUTHOR               NOTE
 * ----------------------------------------------------
 * 2024-07-28        jack8              init create
 * </pre>
 */

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/signup")
    public String signup(@ModelAttribute AddMemberRequest request, Model model) {
        memberService.save(request);
        model.addAttribute("message", "Signup successful. Please log in.");

        // 회원가입 완료 후 로그인 페이지로 이동
        return "redirect:/login";
    }
}