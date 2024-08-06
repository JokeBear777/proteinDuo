package com.proteinduo.domain.memberManage.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName    : com.proteinduo.domain.memberManage.controller
 * fileName       : MemberViewController
 * author         : 82102
 * date           : 2024-08-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-05        82102       최초 생성
 */

@Controller
public class MemberViewController {
    //로그인 뷰
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    
    //회원가입 뷰
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
    
    //로그아웃 뷰
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

}
