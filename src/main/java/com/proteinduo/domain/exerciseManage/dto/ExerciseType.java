package com.proteinduo.domain.exerciseManage.dto;

import lombok.Getter;

/**
 * 사용자 요구에 따라 계속해서 운동 종류를 업데이트 한다
 * 
 */

@Getter
public enum ExerciseType {

    // 가슴 운동
    CHEST_PRESS_MACHINE("체스트 프레스 머신"),
    CHEST_FLY_MACHINE("체스트 플라이 머신"),
    BENCH_PRESS("벤치 프레스"),
    INCLINE_BENCH_PRESS("인클라인 벤치 프레스"),
    DUMBBELL_FLY("덤벨 플라이"),

    // 등 운동
    LAT_PULLDOWN("렛 풀다운"),
    SEATED_ROW_MACHINE("시티드 로우 머신"),
    PULL_UP("풀업"),
    DEADLIFT("데드리프트"),
    T_BAR_ROW("티바 로우"),

    // 어깨 운동
    SHOULDER_PRESS_MACHINE("숄더 프레스 머신"),
    DUMBBELL_SHOULDER_PRESS("덤벨 숄더 프레스"),
    LATERAL_RAISE_MACHINE("레터럴 레이즈 머신"),
    DUMBBELL_LATERAL_RAISE("덤벨 레터럴 레이즈"),
    REAR_DELT_FLY("리어 델트 플라이"),

    // 팔 운동
    // 이두근
    BICEP_CURL_MACHINE("바이셉 컬 머신"),
    DUMBBELL_CURL("덤벨 컬"),
    BARBELL_CURL("바벨 컬"),
    HAMMER_CURL("해머 컬"),
    PREACHER_CURL("프리처 컬"),

    // 삼두근
    TRICEP_DIP_MACHINE("트라이셉 딥 머신"),
    CABLE_TRICEP_PUSH_DOWN("케이블 트라이셉 푸시 다운"),
    TRICEP_EXTENSION("트라이셉 익스텐션"),
    SKULL_CRUSHER("스컬 크러셔"),
    CLOSE_GRIP_BENCH_PRESS("클로즈 그립 벤치 프레스"),

    // 하체 운동
    LEG_PRESS_MACHINE("레그 프레스 머신"),
    SQUAT("스쿼트"),
    LEG_EXTENSION_MACHINE("레그 익스텐션 머신"),
    LEG_CURL_MACHINE("레그 컬 머신"),
    LUNGE("런지"),
    HACK_SQUAT_MACHINE("핵 스쿼트 머신"),
    CALF_RAISE_MACHINE("칼프 레이즈 머신"),

    // 복근 운동
    CRUNCH_MACHINE("크런치 머신"),
    LEG_RAISE("레그 레이즈"),
    PLANK("플랭크"),
    CABLE_AB_CRUNCH("케이블 복근 크런치"),
    RUSSIAN_TWIST("러시안 트위스트"),

    // 전신 운동
    CABLE_CROSSOVER("케이블 크로스오버"),
    SMITH_MACHINE("스미스 머신"),
    KETTLEBELL_SWING("케틀벨 스윙"),
    BURPEES("버피"),
    MEDICINE_BALL_SLAM("메디신 볼 슬램"),

    // 심혈관 운동
    TREADMILL("트레드밀"),
    STATIONARY_BIKE("스테이셔너리 바이크"),
    ROWING_MACHINE("로잉 머신"),
    ELLIPTICAL("엘립티컬"),
    STAIR_CLIMBER("스테어 클라이머"),

    // 기타 운동
    BATTLE_ROPE("배틀 로프"),
    BOX_JUMP("박스 점프"),
    SLED_PUSH("슬레드 푸시"),
    LANDMINE_PRESS("랜드마인 프레스"),
    FARMERS_WALK("파머스 워크");

    private final String description;

    ExerciseType(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return description;
    }
}