package com.proteinduo.domain.routineManage.controller;

import com.proteinduo.domain.memberManage.entity.Member;
import com.proteinduo.domain.routineManage.dto.CreateRoutineDto;
import com.proteinduo.domain.routineManage.dto.RoutineUpdateDto;
import com.proteinduo.domain.routineManage.entity.Routine;
import com.proteinduo.domain.routineManage.service.RoutineSecurityService;
import com.proteinduo.domain.routineManage.service.RoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

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
    private final RoutineSecurityService routineSecurityService;

    @Autowired
    public RoutineController(RoutineService routineService,
                             RoutineSecurityService routineSecurityService) {
        this.routineService = routineService;
        this.routineSecurityService = routineSecurityService;
    }

    // 사용자 루틴 매핑
    @GetMapping
    public String getMemberRoutine(Authentication authentication, Model model) {
        Member member = (Member) authentication.getPrincipal();
        String memberId = member.getMemberId();

        List<Routine> routines = routineService.getRoutinesByMemberId(memberId);

        model.addAttribute("routines", routines);
        return "routineList";
    }

    // 루틴 정보 매핑
    @GetMapping("/{routineId}")
    @PreAuthorize("@routineSecurityService.hasAccessToRoutine(#routineId, principal)")
    public String getRoutine(@PathVariable Integer routineId, Principal principal, Model model) {
        Optional<Routine> routine = routineService.getRoutineById(routineId);

        if (routine.isPresent()) {
            model.addAttribute("routine", routine.get());
            return "routineDetail";
        } else {
            throw new IllegalArgumentException("Routine not found for ID: " + routineId);
        }
    }

    // 루틴 생성
    @PostMapping()
    public String createRoutine(Principal principal, @ModelAttribute CreateRoutineDto createRoutineDto, Model model) {
        String memberId = principal.getName();
        Routine routine = routineService.createRoutine(memberId, createRoutineDto);
        model.addAttribute("routine", routine);

        // 루틴 생성 후 루틴 목록 페이지로 리다이렉트
        return "redirect:/routine";
    }

    // 루틴 정보 수정
    @PostMapping("/{routineId}")
    @PreAuthorize("@routineSecurityService.hasAccessToRoutine(#routineId, principal)")
    public String updateRoutine(@PathVariable Integer routineId, @ModelAttribute RoutineUpdateDto updateDto, Model model) {
        Optional<Routine> routine = routineService.getRoutineById(routineId);

        if (routine.isPresent()) {
            routineService.updateRoutine(routineId, updateDto);
            model.addAttribute("routine", routine.get());
            return "redirect:/routine/" + routineId; // 수정 후 루틴 세부 페이지로 리다이렉트
        } else {
            throw new IllegalArgumentException("Routine not found for ID: " + routineId);
        }
    }

    // 루틴 삭제
    @DeleteMapping("/{routineId}")
    @PreAuthorize("@routineSecurityService.hasAccessToRoutine(#routineId, principal)")
    public String deleteRoutine(@PathVariable Integer routineId) {
        Optional<Routine> routine = routineService.getRoutineById(routineId);

        if (routine.isPresent()) {
            routineService.deleteRoutine(routineId);
            return "redirect:/routine"; // 삭제 후 루틴 목록 페이지로 리다이렉트
        } else {
            throw new IllegalArgumentException("Routine not found for ID: " + routineId);
        }
    }
}
