package com.proteinduo.domain.routineManage.entity;

import com.proteinduo.domain.exerciseManage.entity.Exercise;
import com.proteinduo.domain.memberManage.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    private Integer routineId;

    @Column(name = "routine_name", nullable = false, updatable = true)
    private String routineName;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    //주 몇회
    @Column(name = "per_week", nullable = false, updatable = true)
    private Integer perWeek;

    @ManyToOne
    @JoinColumn(name = "member_id") // 외래 키 컬럼의 이름을 지정
    private Member member;

    @OneToMany(mappedBy = "routine")
    private List<Exercise> exercise;

    @Builder
    public Routine(Member member, String routineName, LocalDate createdAt, Integer perWeek) {
        this.member = member;
        this.routineName = routineName;
        this.createdAt = createdAt;
        this.perWeek = perWeek;
    }


}
