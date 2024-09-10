package com.proteinduo.domain.memberManage.service;


import com.proteinduo.domain.memberManage.entity.Member;
import com.proteinduo.domain.memberManage.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class MemberSecurityService {

    private final MemberRepository memberRepository;

    public MemberSecurityService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * principal 객체를 통해 사용자가 request 한 Id가 valid 한지 검증한다
     *
     * @return
     */
    public boolean hasAccessToMember(Long Id, Principal principal) {
        String currentUserName = principal.getName();
        Optional<Member> member = memberRepository.findById(Id);

        return member.map(r -> r.getMemberId().equals(currentUserName))
                .orElse(false);


    }


}
