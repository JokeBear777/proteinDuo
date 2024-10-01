package com.proteinduo.domain.exerciseManage.controller;


import com.proteinduo.domain.exerciseManage.dto.AddExerciseRecordDto;
import com.proteinduo.domain.exerciseManage.dto.GetExerciseRecordInfoDto;
import com.proteinduo.domain.exerciseManage.dto.UpdateExerciseRecordDto;
import com.proteinduo.domain.exerciseManage.service.ExerciseRecordService;
import com.proteinduo.domain.matchingManage.service.MatchingRegisterService;
import com.proteinduo.domain.memberManage.entity.Member;
import com.proteinduo.domain.memberManage.enums.MatchingState;
import com.proteinduo.domain.memberManage.service.MemberService;
import com.proteinduo.infrastructure.security.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/routine/{routineId}/exercise/{exerciseId}/exerciseRecord")
public class ExerciseRecordController {

    private final ExerciseRecordService exerciseRecordService;
    private final MatchingRegisterService matchingRegisterService;
    private final MemberService memberService;


    @Autowired
    public ExerciseRecordController(ExerciseRecordService exerciseRecordService,
                                    MatchingRegisterService matchingRegisterService,
                                    MemberService memberService) {
        this.exerciseRecordService = exerciseRecordService;
        this.matchingRegisterService = matchingRegisterService;
        this.memberService = memberService;
    }

    @GetMapping
    @PreAuthorize("@exerciseSecurityService.hasAccessToExerciseAndRoutine(#exerciseId, #routineId, principal)")
    public String getExerciseRecord(@PathVariable("routineId") Long routineId,
                                    @PathVariable("exerciseId") Long exerciseId, Model model) {
        /**
        //(매칭 했을 시) 기간별 기록 전체 보기(1개이상) + 매칭 상대의 기록또한 보여줌, 0개시 기록 찾을 수 없음으로 처리. 그레프 포함
        if (memberService.isMatching() == MatchingState.Matched) {
            List<GetExerciseRecordInfoDto> getExerciseRecordInfoDtoList = exerciseRecordService
                    .getExerciseRecordsInfo(exerciseId);
            //매칭 상대 기록도 모델에 추가, 최근 한달 기록 가져오자
            ////공사예정
            wwwww
            model.addAttribute("getExerciseRecordInfoDtoList", getExerciseRecordInfoDtoList);
            return "exerciseRecord/matchted";
        }
        //기간별 기록 전체 보기(1개 이상), 0개시 기록 찾을 수 없음으로 처리, 그래프 포함, (매칭 안했을 시)
        else {
            List<GetExerciseRecordInfoDto> getExerciseRecordInfoDtoList = exerciseRecordService
                    .getExerciseRecordsInfo(exerciseId);
            model.addAttribute("getExerciseRecordInfoDtoList", getExerciseRecordInfoDtoList);
            return "exerciseRecordList";
            //뷰 요구사항 : 매칭을 이용할수 있도록 홍보하자, 이 애플리케이션의 아이덴티티임
        }
         **/
        List<GetExerciseRecordInfoDto> getExerciseRecordInfoDtoList = exerciseRecordService
                .getExerciseRecordsInfo(exerciseId);
        model.addAttribute("getExerciseRecordInfoDtoList", getExerciseRecordInfoDtoList);
        return "exerciseRecordList";

    }

    /**
    //최고기록 보여주기, 그래프 포함, 위 메서드랑 통합할지 고민중..
    @GetMapping("/best-record")
    @PreAuthorize("@exerciseSecurityService.hasAccessToExerciseAndRoutine(#exerciseId, #routineId, principal)")
    public String getBestExerciseRecordInfo(@PathVariable("exerciseId") Long exerciseId, Model model) {
        GetExerciseRecordInfoDto getExerciseRecordInfoDto= exerciseRecordService.getBestExerciseRecordInfo(exerciseId);
        model.addAttribute("getExerciseRecordInfoDto", getExerciseRecordInfoDto);
        return "exerciseRecord/best";
    }
    **/

    //단일 ExerciseRecord 보기
    @GetMapping("/{exerciseRecordId}")
    @PreAuthorize("@exerciseSecurityService.hasAccessToExerciseAndRoutine(#exerciseId, #routineId, principal)")
    public String getExerciseRecord(@PathVariable("exerciseId") Long exerciseId,
                                    @PathVariable("routineId") Long routineId,
                                    @PathVariable("exerciseRecordId") Long exerciseRecordId,
                                    Model model) {
        GetExerciseRecordInfoDto getExerciseRecordInfoDto= exerciseRecordService.getExerciseRecordInfo(exerciseRecordId);
        model.addAttribute("getExerciseRecordInfoDto", getExerciseRecordInfoDto);
        return "exerciseRecord";
    }


    //Record 생성하기
    @PostMapping
    @PreAuthorize("@exerciseSecurityService.hasAccessToExerciseAndRoutine(#exerciseId, #routineId, principal)")
    public String createExerciseRecord(@PathVariable("exerciseId") Long exerciseId,
                                       @PathVariable("routineId") Long routineId,
                                       Model model,
                                       @ModelAttribute AddExerciseRecordDto addExerciseRecordDto) {
        exerciseRecordService.createExerciseRecord(exerciseId, addExerciseRecordDto);
        //오늘 운동 했으므로, 멤버 엔티티에 오늘 날짜 기록
        Long memberDbId = SecurityUtil.getCurrentUserId();
        memberService.addWorkoutDate(memberDbId);
        return String.format("redirect:/routine/%d/exercise/%d/ExerciseRecord", routineId, exerciseId);
    }

    //Record 수정하기
    @PostMapping("/{exerciseRecordId}")
    @PreAuthorize("@exerciseSecurityService.hasAccessToExerciseAndRoutine(#exerciseId, #routineId, principal)")
    public String updateExerciseRecord(@PathVariable("exerciseId") Long exerciseId,
                                       @PathVariable("routineId") Long routineId,
                                       @PathVariable("exerciseRecordId")Long exerciseRecordID,
                                       @ModelAttribute UpdateExerciseRecordDto updateExerciseRecordDto) {
        exerciseRecordService.updateExerciseRecord(exerciseRecordID,updateExerciseRecordDto);
        return String.format("redirect:/routine/%d/exercise/%d/ExerciseRecord/%d/", routineId, exerciseId, exerciseRecordID);

    }

    
    //Record 삭제하기
    @DeleteMapping("/{exerciseRecordId}")
    @PreAuthorize("@exerciseSecurityService.hasAccessToExerciseAndRoutine(#exerciseId, #routineId, principal)")
    public String deleteExerciseRecord(@PathVariable("exerciseId") Long exerciseId,
                                       @PathVariable("routineId") Long routineId,
                                       @PathVariable("exerciseRecordId")Long exerciseRecordID) {
        exerciseRecordService.deleteExerciseRecord(exerciseRecordID);
        return String.format("redirect:/routine/%d/exercise/%d/ExerciseRecord", routineId, exerciseId);

    }




}
