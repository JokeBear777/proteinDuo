package com.proteinduo.application.scheduling.task;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    /**
     * MatchingRegister 바탕으로 매일 자정 최적의 매칭
     * Stable Matching Algorithm사용
     * 홀수일경우 -> 1대1 매칭이 불가능하므로, 가장 늦게 등록한 사용자의 경우 다음날 매치메이킹 참가(1 순위부여)
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void executeMatchingTask() {

    }

    /**
     * 매일 자정, 매칭된 두 사용자끼리의 운동정보 업데이트
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void executeGetPartnerExerciseInfoTask(){
        
    }
}
