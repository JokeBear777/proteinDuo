package com.proteinduo.domain.exerciseManage.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "exerciseRecord")
@Getter
@NoArgsConstructor
@Builder
@Setter
public class ExerciseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_record_id", nullable = false)
    private Long exerciseRecordId;

    //중량
    @Column(name = "weight")
    private Double weight;

    //횟수
    @Column(name = "reps")
    private Integer reps;

    //쉬는 시간, 단위(초)
    @Column(name = "restTime")
    private Integer restTime;

    //생성 시간
    @CreationTimestamp
    @Column(name = "createdAt")
    private Date createdAt;

    /**
     * 단방향 매핑으로 수정, 추후 완료되면 이 부분은 삭제할 수 있도록
     */
    /** 
    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;
     **/

    @Builder
    public ExerciseRecord(Long exerciseRecordId, Double weight, Integer reps, Integer restTime, Date createdAt) {
        this.exerciseRecordId = exerciseRecordId;
        this.weight = weight;
        this.reps = reps;
        this.restTime = restTime;
    }


}
