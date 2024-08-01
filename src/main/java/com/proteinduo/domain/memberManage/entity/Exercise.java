package com.proteinduo.domain.memberManage.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "exercise")
@Getter
@NoArgsConstructor
public class Exercise {

    @Id
    @Column(name = "exercise_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer exerciseId;

    @Column(name="user_id", nullable = false)
    private String userId;

    @Column(name = "routine_name", nullable = false)
    private String routineName;

    @Column(name = "exercise_name", nullable = false)
    private String exerciseName;

    @Column(name = "weight", nullable = false) //kg
    private Integer weight;

    @Column(name = "reps", nullable = false)
    private Integer reps;

    @Column(name = "sets", nullable = false)
    public Integer sets;

    @Column(name = "rest_time",nullable = false) //쉬는 시간 단위(초)
    public Integer restTime;
    
    @Column(name="cycle", nullable = false) //운동주기 단위(주 몇회)
    public Integer cycle;

    @Column(name = "created_at", nullable = false)
    public LocalDate createdAt;

    @Builder
    public Exercise(Integer exerciseId, String userId, String routineName, String exerciseName, Integer weight,
                    Integer reps, Integer sets, Integer restTime, Integer cycle, LocalDate createdAT){
        this.exerciseId = exerciseId;
        this.userId = userId;
        this.routineName = routineName;
        this.exerciseName = exerciseName;
        this.weight = weight;
        this.reps = reps;
        this.sets = sets;
        this.restTime = restTime;
        this.cycle = cycle;
        this.createdAt = createdAT;

    }


}
