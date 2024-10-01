package com.proteinduo.domain.routineManage.entity;

import com.proteinduo.domain.exerciseManage.entity.Exercise;
import com.proteinduo.domain.memberManage.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.proteinduo.domain.routineManage.entity
 * fileName       : Routine
 * author         : 82102
 * date           : 2024-08-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-11        82102       최초 생성
 */


@Entity
@Table(name = "routine")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Routine {


    @Id
    @Column(name = "routine_id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routineId;

    @Column(name = "routine_name", nullable = false, updatable = true)
    private String routineName;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    //주 몇회
    @Column(name = "per_week", nullable = false, updatable = true)
    private Integer perWeek;

    //비연관 매핑
    @Column(name = "member_long_id", nullable = false, updatable = false)
    private Long memberLongId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id")
    private List<Exercise> exercises = new ArrayList<>();

    @Builder
    public Routine(String routineName, LocalDate createdAt, Integer perWeek, Long memberLongId) {
        this.routineName = routineName;
        this.createdAt = createdAt;
        this.perWeek = perWeek;
        this.memberLongId = memberLongId;
    }


}
