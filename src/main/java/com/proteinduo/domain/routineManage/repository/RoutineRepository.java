package com.proteinduo.domain.routineManage.repository;

import com.proteinduo.domain.exerciseManage.entity.Exercise;
import com.proteinduo.domain.memberManage.entity.Member;
import com.proteinduo.domain.routineManage.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.proteinduo.domain.routineManage.repository
 * fileName       : RoutineRepository
 * author         : 82102
 * date           : 2024-08-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-11        82102       최초 생성
 */
@Repository
public interface RoutineRepository extends JpaRepository<Routine, Integer> {

    Optional<Routine> findByRoutineId(Integer routineId);
    void deleteByRoutineId(Integer routineId);
    Boolean existsByRoutineId(Integer routineId);

    List<Routine> findByMember_MemberId(String memberId);
   // List<Exercise> exercises = routineOptional.get().getExercises();
}

