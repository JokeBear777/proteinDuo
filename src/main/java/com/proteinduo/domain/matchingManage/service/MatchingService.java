package com.proteinduo.domain.matchingManage.service;

import com.proteinduo.domain.exerciseManage.dto.GetExerciseRecordInfoDto;
import com.proteinduo.domain.matchingManage.dto.GetMatchingInfoDto;
import com.proteinduo.domain.matchingManage.entity.Matching;
import com.proteinduo.domain.matchingManage.repository.MatchingRepository;
import com.proteinduo.domain.memberManage.entity.Member;
import com.proteinduo.domain.memberManage.repository.MemberRepository;
import com.proteinduo.global.exception.exception.PeriodNotSatisfiedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class MatchingService {
    private final MatchingRepository matchingRepository;
    private final MemberRepository memberRepository;

    public MatchingService(MatchingRepository matchingRepository,
                           MemberRepository memberRepository) {
        this.matchingRepository = matchingRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = true)
    public GetMatchingInfoDto getMatchingInfoByMemberDbId(Long memberDbId) {

        Member member = memberRepository.findById(memberDbId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        Matching matching = member.getMatching();

        GetMatchingInfoDto getMatchingInfoDto = GetMatchingInfoDto.builder()
                .matchingId(matching.getMatchingId())
                .memberId(matching.getMemberId())
                .partnerId(matching.getPartnerId())
                .build();

        return getMatchingInfoDto;
    }

    @Transactional(readOnly = true)
    public Long getMatchingIdByMemberDbId(Long memberDbId) {
        Member member = memberRepository.findById(memberDbId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        return member.getMatching().getMatchingId();
    }

    @Transactional
    public void deleteMatchingOlderThanNDaysByMemberDbId(Long matchingId, int nDays) {
        Matching matching = matchingRepository.findById(matchingId)
                .orElseThrow(()-> new IllegalArgumentException("Matching not found"));

        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime matchingStartTime = matching.getStartTime();

        Duration duration = Duration.between(matchingStartTime, currentDateTime);

        long daysGap = duration.toDays();

        if (daysGap > nDays) {
            matchingRepository.deleteById(matchingId);
        }
        else {
            throw new PeriodNotSatisfiedException("매칭을 삭제하려면 최소" + nDays +  " 일이 지나야 합니다.");
        }
    }

    /**
    @Transactional(readOnly = true)
    public GetExerciseRecordInfoDto getPartnerExerciseRecordInfoByMemberDbId(Long memberDbId) {



    }
    **/


}
