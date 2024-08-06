package com.proteinduo.domain.memberManage.service;

import com.proteinduo.domain.memberManage.repository.MemberRepository;
import com.proteinduo.domain.memberManage.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.proteinduo.domain.memberManage.service
 * fileName       : MemberDetailService
 * author         : 82102
 * date           : 2024-08-02
 * description    : 스프링 시큐리티에서 로그인할때 사용자 정보를 가지고옴
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-02        82102       최초 생성
 */
@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {


    private final MemberRepository memberRepository;
    
    //사용자 memberID으로 사용자 정보를 가져오는 메서드
    @Override
    public Member loadUserByUsername(String name) {
        return memberRepository.findByMemberId(name)
                .orElseThrow(()-> new IllegalArgumentException(name));
    }

}

