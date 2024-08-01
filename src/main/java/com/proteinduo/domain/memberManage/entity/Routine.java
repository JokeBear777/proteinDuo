package com.proteinduo.domain.memberManage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Table(name = "routine")
@Getter
@NoArgsConstructor
public class Routine {

    @Id
    @Column(name = "routine_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer routineId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "routine_name", nullable = false)
    private String routineName;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;


}
