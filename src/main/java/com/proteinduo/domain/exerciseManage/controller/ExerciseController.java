package com.proteinduo.domain.exerciseManage.controller;


import com.proteinduo.domain.exerciseManage.dto.AddExerciseDto;
import com.proteinduo.domain.exerciseManage.dto.UpdateExerciseDto;
import com.proteinduo.domain.exerciseManage.entity.Exercise;
import com.proteinduo.domain.exerciseManage.service.ExerciseService;
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
        Optional<Routine> routineOptional = routineService.getRoutineById(routineId);

        if (routineOptional.isPresent()) {
            List<Exercise> exercises = routineOptional.get().getExercises();
            model.addAttribute("exercises", exercises); // 모델에 데이터를 담음
            return "exerciseList"; // 렌더링할 템플릿 이름 반환
        } else {
            throw new IllegalArgumentException("Routine not found for ID: " + routineId);
        }
    }

    // 운동 생성
    @PostMapping()
    @PreAuthorize("@routineSecurityService.hasAccessToRoutine(#routineId, principal)")
    public String createExercise(@PathVariable Integer routineId, @ModelAttribute AddExerciseDto addExerciseDto, Model model) {
        Exercise exercise = exerciseService.createExercise(addExerciseDto, routineId);
        model.addAttribute("exercise", exercise);
        return "redirect:/routine/" + routineId + "/exercise"; // 운동 생성 후 목록 페이지로 리다이렉트
    }

    // 운동 조회
    @GetMapping("/{exerciseId}")
    @PreAuthorize("@exerciseSecurityService.hasAccessToExerciseAndRoutine(#exerciseId, #routineId, principal)")
    public String getExercise(@PathVariable Long exerciseId, @PathVariable Integer routineId, Model model) {
        return exerciseService.getExerciseByExerciseId(exerciseId)
                .map(exercise -> {
                    model.addAttribute("exercise", exercise);
                    return "exerciseDetail"; // 운동 세부 페이지 템플릿 반환
                })
                .orElse("error/404"); // 존재하지 않는 경우 404 오류 페이지 반환
    }

    // 운동 업데이트
    @PostMapping("/{exerciseId}")
    @PreAuthorize("@exerciseSecurityService.hasAccessToExerciseAndRoutine(#exerciseId, #routineId, principal)")
    public String updateExercise(
            @PathVariable Integer routineId, @PathVariable Long exerciseId,
            @ModelAttribute UpdateExerciseDto updateExerciseDto, Model model) {

        Optional<Exercise> exerciseOptional = exerciseService.getExerciseByExerciseId(exerciseId);

        if (exerciseOptional.isPresent()) {
            Exercise updatedExercise = exerciseService.updateExercise(exerciseId, updateExerciseDto);
            model.addAttribute("exercise", updatedExercise);
            return "exerciseDetail"; // 수정된 후 운동 세부 페이지로 이동
        } else {
            throw new IllegalArgumentException("Exercise not found for ID: " + exerciseId);
        }
    }

   //운동 삭제
    @DeleteMapping("/{exerciseId}")
    @PreAuthorize("@exerciseSecurityService.hasAccessToExerciseAndRoutine(#exerciseId, #routineId, principal)")
    public String deleteExercise(@PathVariable Long exerciseId, @PathVariable Integer routineId
    , Model model) {
        Optional<Exercise> exerciseOptional = exerciseService.getExerciseByExerciseId(exerciseId);

        if (exerciseOptional.isPresent()) {
            exerciseService.deleteExercise(exerciseId);
            return "redirect:/routine/" + routineId + "/exercise";
        } else {
            throw new IllegalArgumentException("Exercise not found for ID: " + exerciseId);
        }

    }

}
