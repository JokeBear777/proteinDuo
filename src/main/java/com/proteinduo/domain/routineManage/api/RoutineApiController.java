package com.proteinduo.domain.routineManage.api;

import com.proteinduo.domain.memberManage.repository.MemberRepository;
import com.proteinduo.domain.routineManage.dto.CreateRoutineDto;
import com.proteinduo.domain.routineManage.dto.RoutineUpdateDto;
import com.proteinduo.domain.routineManage.entity.Routine;
import com.proteinduo.domain.routineManage.service.RoutineService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

@RestController
@RequestMapping("/routine")
public class RoutineApiController {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


    private final RoutineService routineService;

    @Autowired
    public RoutineApiController(RoutineService routineService) {
        this.routineService = routineService;
    }



    //사용자 루틴 매핑
    @GetMapping
    public ResponseEntity<List<Routine>> getMemberRoutine(Principal principal) {
        try {

            String memberId = principal.getName();
            List<Routine> routines = routineService.getRoutinesByMemberId(memberId);
            return new ResponseEntity<>(routines, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            // 기타 서버 에러가 발생한 경우
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



    //루틴 정보 매핑
    @GetMapping("/{routineId}")
    public ResponseEntity<Routine> getRoutine(@PathVariable Integer routineId) {
        Optional<Routine> routine = routineService.getRoutineById(routineId);

        if (routine.isPresent()) {
            return new ResponseEntity<>(routine.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //루틴 생성
    @PostMapping()
    public ResponseEntity<Routine> createRoutine(
            Principal principal, @RequestBody CreateRoutineDto createRoutineDto) {
        try {

            String memberId = principal.getName();
            Routine routine = routineService.createRoutine(memberId, createRoutineDto);
            return new ResponseEntity<>(routine, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            // 기타 서버 에러가 발생한 경우
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    //루틴정보 수정
    @PostMapping("/{routineId}")
    public ResponseEntity<Routine> updateRoutine(
            @PathVariable Integer routineId, @RequestBody RoutineUpdateDto updateDto)
    {
        Optional<Routine> routine = routineService.getRoutineById(routineId);

        if (routine.isPresent()) {
            routineService.updateRoutine(routineId, updateDto);
            return new ResponseEntity<>(routine.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //루틴 삭제
    @DeleteMapping("/{routineId}")
    public ResponseEntity<String> deleteRoutine(@PathVariable Integer routineId) {
        Optional<Routine> routine = routineService.getRoutineById(routineId);

        if (routine.isPresent()) {
            routineService.deleteRoutine(routineId);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }




}
