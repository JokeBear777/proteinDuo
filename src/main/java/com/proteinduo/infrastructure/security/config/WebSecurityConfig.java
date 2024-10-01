package com.proteinduo.infrastructure.security.config;

import com.proteinduo.infrastructure.security.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * packageName    : com.proteinduo.domain.memberManage.config
 * fileName       : WebSecurityConfig
 * author         : 82102
 * date           : 2024-08-02
 * description    : 스프링 시큐리티 설정 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-02        82102       최초 생성
 */
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final UserDetailService userDetailService;
    //private final CustomUserDetailsService userDetailService;

    /**
     * 스프링 시큐리티 기능 비활성화
     *
     * @return the web security customizer
     */
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers("/static/**"); //정적리소스에 대한 보안검사는 하지 않는다
    }

    /**
     * 특정 HTTP요청에 대한 웹 기반 보안 구성
     *
     * @param http the http
     * @return the security filter chain
     * @throws Exception the exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/", "/login/**", "/signup/**", "/static/**").permitAll() //메인화면, 로그인, 회원가입 접근허용
                                .anyRequest().authenticated()
                )
                //폼 기반 로그인 설정
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true) //성공하면 home 뷰로
                        .permitAll()
                )
                //로그아웃 설정
                .logout((log) -> log
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true))
                
                //csrf 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    //인증 관리자 권한 설정
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder
            , UserDetailService userDetailService) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(userDetailService) // 사용자 정보 서비스 설정
                .passwordEncoder(bCryptPasswordEncoder);

        return authenticationManagerBuilder.build();
    }
    
    //패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
