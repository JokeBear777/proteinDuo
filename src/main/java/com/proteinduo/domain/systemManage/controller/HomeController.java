package com.proteinduo.domain.systemManage.controller;


import com.proteinduo.domain.exerciseManage.dto.GetExerciseRecordInfoDto;
import com.proteinduo.domain.exerciseManage.service.ExerciseRecordService;
import com.proteinduo.domain.memberManage.enums.MatchingState;
import com.proteinduo.domain.memberManage.service.MemberService;
import com.proteinduo.domain.routineManage.service.RoutineService;
import com.proteinduo.infrastructure.security.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class HomeController {
    private final ExerciseRecordService exerciseRecordService;
    private final RoutineService routineService;
    private final MemberService memberService;

    @Autowired
    public HomeController(ExerciseRecordService exerciseRecordService,
                          RoutineService routineService,
                          MemberService memberService) {
        this.exerciseRecordService = exerciseRecordService;
        this.routineService = routineService;
        this.memberService = memberService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        //최근 한달동안 운동 얼마나했는지 달력 보여줌, 나와 파트너의 exerciseRecord 활용
        Long memberDbId = SecurityUtil.getCurrentUserId();
        List<LocalDateTime> myMonthlyWorkoutDates = memberService.getMonthlyWorkoutDates(memberDbId);
        model.addAttribute("myMonthlyWorkoutDates", myMonthlyWorkoutDates);

        if (memberService.isMatching() == MatchingState.Matched) {
            Long partnerDbId = memberService.getPartnerId(memberDbId);
            List<LocalDateTime> partnerMonthlyWorkoutDates = memberService.getMonthlyWorkoutDates(partnerDbId);
            model.addAttribute("partnerMonthlyWorkoutDates", partnerMonthlyWorkoutDates);
        }

        //+ 파트너와 나와의 성장속도, 얼만큼 +됐는지 보여줌,->나중에 업데이트
        //내 루틴들 보여줌, <운동하기> h1밑에 루틴들 보여줌


        return "home";
    }

}
