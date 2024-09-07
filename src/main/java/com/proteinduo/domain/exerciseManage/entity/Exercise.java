package com.proteinduo.domain.exerciseManage.entity;

import com.proteinduo.domain.exerciseManage.dto.ExerciseType;
import com.proteinduo.domain.routineManage.entity.Routine;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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

    @ManyToOne
    @JoinColumn(name = "routine_id")
    private Routine routine;

    @OneToMany(mappedBy = "exercise")
    private List<ExerciseRecord> exerciseRecords;

    @Builder
    public Exercise(Routine routine, ExerciseType exerciseType,  LocalDate createdAT){
        this.routine = routine;
        this.exerciseType = exerciseType;

    }


}
