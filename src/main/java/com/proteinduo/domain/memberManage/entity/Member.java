package com.proteinduo.domain.memberManage.entity;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.proteinduo.domain.matchingManage.entity.Matching;
import com.proteinduo.domain.memberManage.dto.MemberInfoRequest;
import com.proteinduo.domain.memberManage.enums.MatchingState;
import com.proteinduo.domain.routineManage.entity.Routine;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <br>package name   : com.proteinduo.domain.memberManage.entity
 * <br>file name      : Member
 * <br>date           : 2024-07-28
 * <pre>
 * <span style="color: white;">[description]</span>
 *
 * </pre>
 * <pre>
 * <span style="color: white;">usage:</span>
 * {@code
 *
 * } </pre>
 * <pre>
 * modified log :
 * ====================================================
 * DATE           AUTHOR               NOTE
 * ----------------------------------------------------
 * 2024-07-28        jack8              init create
 * </pre>
 */
@Entity
@Table(name = "member")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member { //인증 객체로 사용

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "member_id", nullable = false, unique = true, updatable = false)
    private String memberId;

    @Column(name = "password", nullable = false)
    private String password;

    //이하 사용자 Info
    @Column(name = "username", nullable = true)
    private String username;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "gender", nullable = true)
    private String gender;

    @Column(name = "age", nullable = true)
    private Integer age;

    @Column(name = "height", nullable = true)
    private Integer height;

    @Column(name = "weight", nullable = true)
    private Integer weight;

    @Column(name = "muscle_mass", nullable = true) //골격근량
    private Integer muscleMass;

    @Column(name = "bodyFat", nullable = true) //체지방량
    private Integer bodyFat;

    @Column(name = "bmi", nullable = true)
    private Integer bmi;

    @Column(name = "body_fat_percentage", nullable = true) //체지방률
    private Integer bodyFatPercentage;

    @Column(name = "is_matching", nullable = false)   //매칭 되었는지
    private MatchingState isMatching;

   // 데이터베이스에서는 별도의 테이블이 생성되지만, 컬렉션이 값 자체로 저장
   //운동 기록한 날짜들
    @ElementCollection
    @CollectionTable(name = "workout_dates", joinColumns = @JoinColumn(name = "workout_record_id"))
    @Column(name = "workout_date")
    private List<LocalDateTime> workoutDate = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id")
    private List<Routine> routines = new ArrayList<>();

    //onetoone 매핑이라 래지패치 필요 없음
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "matching_id")
    private Matching matching;



    @Builder
    public Member(String memberId, String password, String username, String email,  String gender,
                 Integer age, Integer height, Integer weight, Integer muscleMass, Integer bodyFat,
                 int bmi, int bodyFatPercentage) {
        this.memberId = memberId;
        this.password = password;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.muscleMass = muscleMass;
        this.bodyFat = bodyFat;
        this.bmi = bmi;
        this.bodyFatPercentage = bodyFatPercentage;
        this.isMatching = MatchingState.NotMatched; //기본값
    }


    public void update(MemberInfoRequest memberInfoRequest) {
        this.username = memberInfoRequest.getUsername();
        this.email = memberInfoRequest.getUsername();
        this.gender = memberInfoRequest.getUsername();
        this.height = memberInfoRequest.getHeight();
        this.weight = memberInfoRequest.getWeight();
        this.muscleMass = memberInfoRequest.getMuscleMass();
        this.bodyFat = memberInfoRequest.getBodyFat();
        this.bmi = memberInfoRequest.getBmi();
        this.bodyFatPercentage = memberInfoRequest.getBodyFatPercentage();
    }

    public void deleteInfo() {
        this.username = null;
        this.email = null;
        this.gender = null;
        this.height = null;
        this.weight = null;
        this.muscleMass = null;
        this.bodyFat = null;
        this.bmi = null;
        this.bodyFatPercentage = null;
    }



}
