package com.proteinduo.domain.matchingManage.entity;


import com.proteinduo.domain.memberManage.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "matching")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Matching {
    //매칭 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long matchingId;

    //내 아이디
    @Column(name = "member_id", nullable = false, unique = true)
    private Long memberId;
    
    //상대 아이디
    @Column(name = "partner_id", nullable = false, unique = true)
    private Long partnerId;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime startTime;

    @Builder
    public Matching(Long memberId, Long partnerId) {
        this.memberId = memberId;
        this.partnerId = partnerId;
    }


    
}
