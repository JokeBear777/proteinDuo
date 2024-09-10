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
    @Transactional
    public Long save(AddMemberRequest dto) {
        Member member = Member.builder()
                .memberId(dto.getMemberId())  // 사용자 입력 ID를 저장 (하지만 기본 키는 Long으로)
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build();
        Member savedMember = memberRepository.save(member);
        return savedMember.getId();  // Long 타입의 ID 반환
    }

    //회원 정보 가져오기
    @Transactional
    public Member getById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + id));
    }

    //회원 정보 입력
    @Transactional
    public Member memberInfoSave(Long id, MemberInfoRequest memberInfoRequest) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + id));
        member.update(memberInfoRequest);
        return memberRepository.save(member);  // 변경된 정보를 저장
    }

    //회원 정보 삭제
    @Transactional
    public void memberInfoDelete(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + id));
        member.deleteInfo();
        memberRepository.delete(member);  // 엔티티를 삭제
    }
}
