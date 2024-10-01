package com.proteinduo.domain.matchingManage.controller;

import com.proteinduo.domain.matchingManage.dto.AddMemberInfoRequestDto;
import com.proteinduo.domain.matchingManage.dto.GetMatchingInfoDto;
import com.proteinduo.domain.matchingManage.service.MatchingRegisterService;
import com.proteinduo.domain.matchingManage.service.MatchingService;
import com.proteinduo.domain.memberManage.enums.MatchingState;
import com.proteinduo.domain.memberManage.service.MemberService;
import com.proteinduo.infrastructure.security.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/matching")
public class MatchingController {

    private final MatchingRegisterService matchingRegisterService;
    private final MemberService memberService;
    private final MatchingService matchingService;

    @Autowired
    public MatchingController(MatchingRegisterService matchingRegisterService,
                              MemberService memberService,
                              MatchingService matchingService) {
        this.matchingRegisterService = matchingRegisterService;
        this.memberService = memberService;
        this.matchingService = matchingService;
    }

    /**
     * 매칭 메인뷰, 매칭 유무에 따라 다른 뷰를 보여준다
     */
    @GetMapping()
    public String getMatchingMainView(Model model) {
        MatchingState currentMatchingState = memberService.isMatching();
        
        if (currentMatchingState == MatchingState.Matched) {////////////매칭에 관한 dto)
            Long currentUserId = SecurityUtil.getCurrentUserId();
            Long matchingId = matchingService.getMatchingIdByMemberDbId(currentUserId);
            return "redirect:/"+ matchingId; //인터섹션 컨트롤러에서 상대방방과 간단한 채팅, 등등
        }
        else if (currentMatchingState == MatchingState.MatchMaking) {
            return "making"; //익일까지 기다려 달라는 뷰 만들자
        }
        else  {
            return "notmatched"; //매칭 등록하는 뷰 보여주자~
        }
       
    }

    /**
     * 매치 메이킹 등록, 익일 0시 이후에 최적의 매칭, 이미 매칭등록했다면 매칭 후 취소 가능
     */
    @PostMapping("/register")
    public String registerMatching(@ModelAttribute AddMemberInfoRequestDto addMemberInfoRequestDto
            , Model model) {
        Long currentUserId = SecurityUtil.getCurrentUserId();
        // 매칭 등록 및 스케쥴링 작업을 서비스로 위임
        matchingRegisterService.registerMatching(currentUserId, addMemberInfoRequestDto);
        return "redirect:/matching";
    }

    @GetMapping("/{matchingId}")
    public String getMatchingInfo(@PathVariable Long matchingId, Model model) {
        Long currentUserId = SecurityUtil.getCurrentUserId();
        GetMatchingInfoDto getMatchingInfoDto = matchingService.getMatchingInfoByMemberDbId(currentUserId);
        model.addAttribute("matchingInfoDto", getMatchingInfoDto);
        
        //////////////////////
        ///좀더 기능 추가 고민해보자
        ////추후 업데이트
        
        return "matchingInfo";
    }

    /**
     * 매칭 취소, 최소 일주일 후 가능, 너무 자주 바꾸면 사용자에게 불쾌한 경험
     * @return
     */
    @DeleteMapping("/{matchingId}")
    public String deleteMatching(@PathVariable Long matchingId) {
        matchingService.deleteMatchingOlderThanNDaysByMemberDbId(matchingId, 7);
        return "redirect:/matching";
    }
    

}
