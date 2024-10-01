package com.proteinduo.domain.memberManage.service;

import com.proteinduo.domain.exerciseManage.dto.GetExerciseRecordInfoDto;
import com.proteinduo.domain.matchingManage.entity.Matching;
import com.proteinduo.domain.memberManage.enums.MatchingState;
import com.proteinduo.infrastructure.security.config.CustomUserDetails;
import com.proteinduo.domain.memberManage.dto.AddMemberRequestDto;
import com.proteinduo.domain.memberManage.dto.MemberInfoRequest;
import com.proteinduo.domain.memberManage.dto.GetMemberInfoDto;
import com.proteinduo.domain.memberManage.entity.Member;
import com.proteinduo.domain.memberManage.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

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
    public Long save(AddMemberRequestDto dto) {
        Member member = Member.builder()
                .memberId(dto.getMemberId())  // 사용자 입력 ID를 저장 (하지만 기본 키는 Long으로)
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build();
        Member savedMember = memberRepository.save(member);
        return savedMember.getId();  // Long 타입의 ID 반환
    }

    //회원 정보 가져오기
    @Transactional
    public GetMemberInfoDto getMemberInfoById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + id));

        GetMemberInfoDto getMemberInfoDto = GetMemberInfoDto.builder()
                .username(member.getUsername())
                .email(member.getEmail())
                .gender(member.getGender())
                .age(member.getAge())
                .height(member.getHeight())
                .weight(member.getWeight())
                .muscleMass(member.getMuscleMass())
                .bodyFat(member.getBodyFat())
                .bmi(member.getBmi())
                .bodyFatPercentage(member.getBodyFatPercentage())
                .build();

        return getMemberInfoDto;
    }

    //회원 정보 입력
    @Transactional
    public void memberInfoSave(Long id, MemberInfoRequest memberInfoRequest) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + id));
        member.update(memberInfoRequest);
        memberRepository.save(member);  // 변경된 정보를 저장
    }

    //회원 정보 삭제
    @Transactional
    public void memberInfoDelete(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + id));
        member.deleteInfo();
        memberRepository.delete(member);  // 엔티티를 삭제
    }

    public MatchingState isMatching() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getIsMatching();
    }

    public void addWorkoutDate(Long memberDbId) {
        Member member = memberRepository.findById(memberDbId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberDbId));

        member.getWorkoutDate().add(LocalDateTime.now());
    }

    public List<LocalDateTime> getMonthlyWorkoutDates(Long memberDbId) {
        Member member = memberRepository.findById(memberDbId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberDbId));

        List<LocalDateTime> localDateTimes = member.getWorkoutDate();
        LocalDateTime now = LocalDateTime.now();

        List<LocalDateTime> monthlyWorkoutDates = new ArrayList<>();

        for(LocalDateTime localDateTime : localDateTimes) {
            long daysBetween = ChronoUnit.DAYS.between(now, localDateTime);

            if (daysBetween <= 30) {
                monthlyWorkoutDates.add(localDateTime);
            }
        }

        return monthlyWorkoutDates;
    }

    public Long getPartnerId(Long memberDbId) {
        Member member = memberRepository.findById(memberDbId)
                .orElseThrow(()->new IllegalArgumentException("Member not found with id: " + memberDbId));
        Matching matching = member.getMatching();
        if (matching == null) {
            throw new IllegalStateException("No matching found for member with id: " + memberDbId);
        }
        return matching.getPartnerId();
    }


}
