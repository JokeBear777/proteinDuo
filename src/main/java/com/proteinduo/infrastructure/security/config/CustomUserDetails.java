package com.proteinduo.infrastructure.security.config;

import com.proteinduo.domain.memberManage.entity.Member;
import com.proteinduo.domain.memberManage.enums.MatchingState;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class CustomUserDetails implements UserDetails {
    private final Long id;
    private final String memberId;
    private final String password;
    private final MatchingState isMatching;

    public CustomUserDetails(Member member) {
        this.id = member.getId();
        this.memberId = member.getMemberId();
        this.password = member.getPassword();
        this.isMatching = member.getIsMatching();
    }

    //멤버 아이디 반환!!
    @Override
    public String getUsername() {
        return this.memberId;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public MatchingState getIsMatching() {
        return this.isMatching;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("user"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
