package com.proteinduo.domain.matchingManage.service;

import com.proteinduo.domain.matchingManage.dto.AddMemberInfoRequestDto;
import com.proteinduo.domain.matchingManage.entity.MatchingRegister;
import com.proteinduo.domain.matchingManage.repository.MatchMakingRepository;
import com.proteinduo.domain.matchingManage.repository.MatchingRepository;
import com.proteinduo.domain.memberManage.entity.Member;
import com.proteinduo.domain.memberManage.enums.MatchingState;
import com.proteinduo.domain.memberManage.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MatchingRegisterService {

    private final MatchingRepository matchingRepository;
    private final MemberRepository memberRepository;
    private final MatchMakingRepository matchMakingRepository;

    @Autowired
    public MatchingRegisterService(MatchingRepository matchingRepository,
                                   MemberRepository memberRepository,
                                   MatchMakingRepository matchMakingRepository) {
        this.matchingRepository = matchingRepository;
        this.memberRepository = memberRepository;
        this.matchMakingRepository = matchMakingRepository;
    }

    /**
     * MatchingRegister에 등록해 놓으면 익일 0시이후 스케쥴링에 의해
     * 자동으로 매칭이 된다.
     *
     */
    @Transactional
    public void registerMatching(Long currentUserId, AddMemberInfoRequestDto addMemberInfoRequestDto) {
        Member member = memberRepository.findById(currentUserId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        member.setIsMatching(MatchingState.MatchMaking);


        MatchingRegister matchingRegister = MatchingRegister.builder()
                .memberId(currentUserId)
                .username(member.getUsername())
                .gender(member.getGender())
                .age(member.getAge())
                .height(member.getHeight())
                .weight(member.getWeight())
                .muscleMass(member.getMuscleMass())
                .bodyFat(member.getBodyFat())
                .bmi(member.getBmi())
                .bodyFatPercentage(member.getBodyFatPercentage())

                .perWeek(addMemberInfoRequestDto.getPerWeek())
                .goal(addMemberInfoRequestDto.getGoal())
                .athleticBackground(addMemberInfoRequestDto.getAthleticBackground())
                .division(addMemberInfoRequestDto.getDivision())
                .build();


        matchMakingRepository.save(matchingRegister);

    }





}
