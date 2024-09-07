package com.proteinduo.domain.exerciseManage.repository;

import com.proteinduo.domain.exerciseManage.entity.Exercise;
import com.proteinduo.domain.routineManage.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.proteinduo.domain.exerciseManage.repository
 * fileName       : ExerciseRepository
 * author         : 82102
 * date           : 2024-08-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-11        82102       최초 생성
 */
@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    Boolean existsByExerciseId(Long exerciseId);
    void deleteByExerciseId(Long Id);

}
