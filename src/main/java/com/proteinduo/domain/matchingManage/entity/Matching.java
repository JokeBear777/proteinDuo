package com.proteinduo.domain.matchingManage.entity;


import com.proteinduo.domain.memberManage.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "matching")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Matching {
    
    //상대 아이디
    @Id
    private Long matchingId;

    @OneToOne(mappedBy = "member")
    private Member member;

    @Builder
    public Matching(Long matchingId) {
        this.matchingId = matchingId;
    }
    
}
