package com.proteinduo.domain.exerciseManage.controller;


import com.proteinduo.domain.exerciseManage.dto.AddExerciseDto;
import com.proteinduo.domain.exerciseManage.dto.GetExerciseInfoDto;
import com.proteinduo.domain.exerciseManage.dto.GetExerciseSimpleInfoDto;
import com.proteinduo.domain.exerciseManage.dto.UpdateExerciseDto;
import com.proteinduo.domain.exerciseManage.entity.Exercise;
import com.proteinduo.domain.exerciseManage.service.ExerciseService;
import com.proteinduo.domain.routineManage.dto.GetRoutineInfoDto;
import com.proteinduo.domain.routineManage.entity.Routine;
import com.proteinduo.domain.routineManage.service.RoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/routine/{routineId}/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final RoutineService routineService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService,
                              RoutineService routineService) {
        this.exerciseService = exerciseService;
        this.routineService = routineService;
    }

    /**
     * 루틴에 속한 운동들을 불러온다
     * return List<Exercise>
     */
    @GetMapping()
    @PreAuthorize("@routineSecurityService.hasAccessToRoutine(#routineId, principal)")
    public String getAllExercises(@PathVariable Integer routineId, Model model) {
        List<GetExerciseSimpleInfoDto> getExerciseSimpleInfoDtoList = routineService.getExercisesByRoutineId(routineId);
        model.addAttribute("getExercisesSimpleInfoDtoList", getExerciseSimpleInfoDtoList); // 모델에 데이터를 담음
        return "exerciseList"; // 렌더링할 템플릿 이름 반환

    }

    // 운동 생성
    @PostMapping()
    @PreAuthorize("@routineSecurityService.hasAccessToRoutine(#routineId, principal)")
    public String createExercise(@PathVariable Integer routineId, @ModelAttribute AddExerciseDto addExerciseDto, Model model) {
        exerciseService.createExercise(addExerciseDto, routineId);
        return "redirect:/routine/" + routineId + "/exercise"; // 운동 생성 후 목록 페이지로 리다이렉트
    }

    /**
     *
     * 운동조회, 본인의 최근 n회 의 exerciseRecords도 함께 반환하여, 뷰에서 그래프로 추이를 그린다
     * 우선 n은 7로 정함, 추후 필요에 따라 유동적으로 변함
     * @return
     */
    @GetMapping("/{exerciseId}")
    @PreAuthorize("@exerciseSecurityService.hasAccessToExerciseAndRoutine(#exerciseId, #routineId, principal)")
    public String getExercise(@PathVariable Long exerciseId, @PathVariable Integer routineId, Model model) {
        GetExerciseInfoDto getExerciseInfoDto =  exerciseService.getExerciseByExerciseId(exerciseId);
        model.addAttribute("exercise", getExerciseInfoDto);
        return "exerciseDetail"; // 운동 세부 페이지 템플릿 반환

    }

    // 운동 업데이트
    @PostMapping("/{exerciseId}")
    @PreAuthorize("@exerciseSecurityService.hasAccessToExerciseAndRoutine(#exerciseId, #routineId, principal)")
    public String updateExercise(
            @PathVariable Integer routineId, @PathVariable Long exerciseId,
            @ModelAttribute UpdateExerciseDto updateExerciseDto, Model model) {

        exerciseService.updateExercise(exerciseId, updateExerciseDto);
        GetExerciseInfoDto getExerciseInfoDto =  exerciseService.getExerciseByExerciseId(exerciseId);
        model.addAttribute("exercise", getExerciseInfoDto);
        return "exerciseDetail"; // 수정된 후 운동 세부 페이지로 이동

    }

   //운동 삭제
    @DeleteMapping("/{exerciseId}")
    @PreAuthorize("@exerciseSecurityService.hasAccessToExerciseAndRoutine(#exerciseId, #routineId, principal)")
    public String deleteExercise(@PathVariable Long exerciseId, @PathVariable Integer routineId) {
        exerciseService.deleteExercise(exerciseId);
        return "redirect:/routine/" + routineId + "/exercise";
    }

}
