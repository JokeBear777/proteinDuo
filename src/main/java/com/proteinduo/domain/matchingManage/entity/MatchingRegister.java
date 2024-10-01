package com.proteinduo.domain.matchingManage.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "matching")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MatchingRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long matchingRegisterId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "username", nullable = true)
    private String username;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "height", nullable = false)
    private Integer height;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @Column(name = "muscle_mass", nullable = false) //골격근량
    private Integer muscleMass;

    @Column(name = "bodyFat", nullable = false) //체지방량
    private Integer bodyFat;

    @Column(name = "bmi", nullable = false)
    private Integer bmi;

    @Column(name = "body_fat_percentage", nullable = false) //체지방률
    private Integer bodyFatPercentage;

    //주에 얼마나(일수)
    @Column(name = "per_week", nullable = false)
    private Integer perWeek;

    //운동 목표, ->>매우 중요
    @Column(name = "goal", nullable = false)
    private String goal;
    //다이어트, 벌크업 ...ets

    //운동 경력(개월)
    @Column(name = "athletic_background", nullable = false)
    private Integer athleticBackground;

    //몇분할
    @Column(name = "division", nullable = false)
    private Integer division;

    //언제 등록했니
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public MatchingRegister(Long memberId, String username,String gender, Integer age,Integer height, Integer weight,
                                 Integer muscleMass, Integer bodyFat,Integer bmi,Integer bodyFatPercentage ,
                                 Integer perWeek, String goal, Integer athleticBackground, Integer division) {
        this.memberId = memberId;
        this.username = username;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.muscleMass = muscleMass;
        this.bodyFat = bodyFat;
        this.bmi = bmi;
        this.bodyFatPercentage = bodyFatPercentage;
        this.perWeek = perWeek;
        this.goal = goal;
        this.athleticBackground = athleticBackground;
        this.division = division;

    }

}
