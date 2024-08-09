package com.proteinduo.domain.memberManage.service;

import com.proteinduo.domain.memberManage.dto.AddMemberRequest;
import com.proteinduo.domain.memberManage.dto.MemberInfoRequest;
import com.proteinduo.domain.memberManage.entity.Member;
import com.proteinduo.domain.memberManage.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <br>package name   : com.proteinduo.domain.memberManage.service
 * <br>file name      : MemberService
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

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    //회원가입
    public String save (AddMemberRequest dto) {
        return memberRepository.save(Member.builder()
                .memberId(dto.getMemberId())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getMemberId();
    }

    //회원 정보 가져오기
    @Transactional
    public Member getMemberById(String memberId) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(()-> new IllegalArgumentException("not found"));

        return member;
    }

    //회원 정보 입력
    @Transactional
    public Member memberInfoSave(String memberId, MemberInfoRequest memberInfoRequest){
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(()-> new IllegalArgumentException("not found"));
        member.update(memberInfoRequest);

        return member;

    }

    //회원 정보 삭제
    @Transactional
    public Member memberInfoDelete(String memberId) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(()-> new IllegalArgumentException("not found"));
        member.deleteInfo();
        return member;
    }




}
