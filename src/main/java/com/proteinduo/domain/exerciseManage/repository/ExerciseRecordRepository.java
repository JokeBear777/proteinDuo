package com.proteinduo.domain.exerciseManage.repository;

import com.proteinduo.domain.exerciseManage.entity.ExerciseRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecord, Long> {

}
