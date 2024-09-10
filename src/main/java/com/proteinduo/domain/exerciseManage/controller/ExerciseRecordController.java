package com.proteinduo.domain.exerciseManage.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/routine/{routineId}/exercise/{exerciseId}/ExerciseRecord")
public class ExerciseRecordController {
    
    //기간별 기록 전체 보기(1개 이상), 0개시 기록 찾을 수 없음으로 처리, 그래프 포함, (매칭 안했을 시)
    
    //(매칭 했을 시) 기간별 기록 전체 보기(1개이상) + 매칭 상대의 기록또한 보여줌, 0개시 기록 찾을 수 없음으로 처리. 그레프 포함

    //최고기록 보여주기, 그래프 포함

    //Record 생성하기

    //Record 수정하기
    
    //Record 삭제하기
}
