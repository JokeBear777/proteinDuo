package com.proteinduo.domain.exerciseManage.entity;

import com.proteinduo.domain.exerciseManage.dto.ExerciseType;
import com.proteinduo.domain.routineManage.entity.Routine;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exercise")
@Getter
@Setter
@NoArgsConstructor
public class Exercise {

    @Id
    @Column(name = "exercise_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exerciseId;

    @Column(name = "exercise_Type", nullable = false)
    private ExerciseType exerciseType;

    //비연관 매핑
    @Column(name = "routine_id", nullable = false, updatable = false)
    private  Long routineId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_record_Id")
    private List<ExerciseRecord> exerciseRecords = new ArrayList<>();

    @Builder
    public Exercise(ExerciseType exerciseType, Long routineId) {
        this.exerciseType = exerciseType;
        this.routineId = routineId;

    }


}
