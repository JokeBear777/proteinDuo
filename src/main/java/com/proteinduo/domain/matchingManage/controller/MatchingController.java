package com.proteinduo.domain.matchingManage.controller;

import com.proteinduo.domain.matchingManage.entity.Matching;
import com.proteinduo.domain.matchingManage.service.MatchingService;
import com.proteinduo.domain.memberManage.entity.Member;
import com.proteinduo.domain.memberManage.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/matching")
public class MatchingController {

    private final MatchingService matchingService;
    private final MemberService memberService;

    @Autowired
    public MatchingController(MatchingService matchingService,
                              MemberService memberService) {
        this.matchingService = matchingService;
        this.memberService = memberService;
    }

    /**
     * 매칭 메인뷰, 매칭 유무에 따라 다른 뷰를 보여준다
     */
    @GetMapping()
    public String showMatchingMainView(Authentication authentication, Model model) {
        Member member = (Member) authentication.getPrincipal();
        Optional<Matching> matchingOptional = Optional.ofNullable(member.getMatching());
        if (matchingOptional.isPresent()) {
            model.addAttribute("matching", matchingOptional.get());
            return "matching";
        }
        else {
            return "notMatching";
        }

    }

    /**
     * 매치 메이킹 등록, 익일 0시 이후에 최적의 매칭, 이미 매칭등록했다면 매칭 취소후 가능
     */
    @PostMapping("/register")
    public String registerMatching(Matching matching, Model model) {
        // 매칭 등록 및 스케쥴링 작업을 서비스로 위임
        
        //성공 페이지로 이동
        


        return;
    }

    /**
     * 매칭 취소(최소 7일후)
     * @return
     */
    @DeleteMapping()
    

}
