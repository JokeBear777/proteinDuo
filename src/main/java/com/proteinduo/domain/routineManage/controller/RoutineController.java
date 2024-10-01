package com.proteinduo.domain.routineManage.controller;

import com.proteinduo.infrastructure.security.config.CustomUserDetails;
import com.proteinduo.domain.routineManage.dto.CreateRoutineDto;
import com.proteinduo.domain.routineManage.dto.GetRoutineInfoDto;
import com.proteinduo.domain.routineManage.dto.GetRoutinesInfoDto;
import com.proteinduo.domain.routineManage.dto.RoutineUpdateDto;
import com.proteinduo.domain.routineManage.service.RoutineService;
import com.proteinduo.infrastructure.security.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName    : com.proteinduo.domain.routineManage.api
 * fileName       : RoutineController
 * author         : 82102
 * date           : 2024-08-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-12        82102       최초 생성
 */

@Controller
@RequestMapping("/routine")
public class RoutineController {

    private final RoutineService routineService;

    @Autowired
    public RoutineController(RoutineService routineService) {
        this.routineService = routineService;
    }


    /**
     * 사용자의 루틴 목록을 조회하여 반환
     *
     * @param model 뷰에 전달할 데이터 모델
     * @return 루틴이 없을 경우 빈 리스트, 있을 경우 루틴 리스트를 반환하여 routineList 템플릿에서 동적으로 처리
     */
    @GetMapping
    public String getMemberRoutine(Model model) {
        /**CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Long Id = customUserDetails.getId();
         **/
        Long Id = SecurityUtil.getCurrentUserId();

        List<GetRoutinesInfoDto> getRoutinesInfoDtos = routineService.getRoutinesByMemberId(Id);
        model.addAttribute("routines", getRoutinesInfoDtos);
        return "routineList";
    }

    // 루틴 정보 매핑
    @GetMapping("/{routineId}")
    @PreAuthorize("@routineSecurityService.hasAccessToRoutine(#routineId, principal)")
    public String getRoutine(@PathVariable Integer routineId, Model model) {
        GetRoutineInfoDto getRoutineInfoDto = routineService.getRoutineById(routineId);
        model.addAttribute("routine", getRoutineInfoDto);
        return "routineDetail";
    }

    // 루틴 생성
    @PostMapping()
    public String createRoutine(@ModelAttribute CreateRoutineDto createRoutineDto, Model model,
                                Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Long Id = customUserDetails.getId();

        routineService.createRoutine(Id, createRoutineDto);
        return "redirect:/routine"; // 루틴 생성 후 루틴 목록 페이지로 리다이렉트
    }

    // 루틴 정보 수정
    @PostMapping("/{routineId}")
    @PreAuthorize("@routineSecurityService.hasAccessToRoutine(#routineId, principal)")
    public String updateRoutine(@PathVariable Integer routineId, @ModelAttribute RoutineUpdateDto updateDto, Model model) {
        routineService.updateRoutine(routineId, updateDto);
        return "redirect:/routine/" + routineId; // 수정 후 루틴 세부 페이지로 리다이렉트
    }

    // 루틴 삭제
    @DeleteMapping("/{routineId}")
    @PreAuthorize("@routineSecurityService.hasAccessToRoutine(#routineId, principal)")
    public String deleteRoutine(@PathVariable Integer routineId) {
        routineService.deleteRoutine(routineId);
        return "redirect:/routine"; // 삭제 후 루틴 목록 페이지로 리다이렉트
    }
}
