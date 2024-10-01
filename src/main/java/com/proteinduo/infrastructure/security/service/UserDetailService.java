package com.proteinduo.infrastructure.security.service;

import com.proteinduo.infrastructure.security.config.CustomUserDetails;
import com.proteinduo.domain.memberManage.repository.MemberRepository;
import com.proteinduo.domain.memberManage.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    // 사용자 ID로 사용자 정보를 가져오는 메서드
    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new CustomUserDetails(member);  // Member 엔티티를 CustomUserDetails로 변환
    }

}

