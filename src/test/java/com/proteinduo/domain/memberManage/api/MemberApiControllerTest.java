package com.proteinduo.domain.memberManage.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proteinduo.domain.memberManage.dto.AddMemberRequestDto;
import com.proteinduo.domain.memberManage.dto.MemberInfoRequest;
import com.proteinduo.domain.memberManage.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * packageName    : com.proteinduo.domain.memberManage.api
 * fileName       : MemberApiControllerTest
 * author         : 82102
 * date           : 2024-08-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-07        82102       최초 생성
 */

@SpringBootTest
@AutoConfigureMockMvc
public class MemberApiControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private MemberService memberService;

    private MockMvc mvc;


    private static ObjectMapper objectMapper = new ObjectMapper();
    private static MvcResult loginResult;

    @BeforeEach
    public void signup() throws Exception {
        //가상 회원가입
        mvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .apply(springSecurity())
                .build();

        AddMemberRequestDto addMemberRequestDto = new AddMemberRequestDto("abcd", "1234");
        memberService.save(addMemberRequestDto);

        // 로그인
        // given
        String userId = "abcd";
        String password = "1234";

        // when
        mvc.perform(formLogin().user(userId).password(password))
                .andDo(print())
                // then
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));


    }


    @Test
    @WithMockUser(username = "abcd")
    @DisplayName("getMemberInfo() : 유저의 정보를 가지고 온다")
    public void testGetMemberInfo() throws Exception {

        mvc.perform(get("/member/{memberId}", "abcd"))
                .andDo(print())
                .andExpect(status().isOk());
    }



    @Test
    @WithMockUser(username = "abcd")
    @DisplayName("registerMemberInfo() : 유저의 정보를 등록/수정 한다")
    void registerMemberInfo() throws Exception{
        //given
        MemberInfoRequest memberInfoRequest = new MemberInfoRequest(

                "unikal",
                "test@example.com",
                "male",
                177,
                110,
                35,
                15,
                23,
                18
        );

        //when&&then
        mvc.perform(MockMvcRequestBuilders.post("/member/{memberId}", "abcd")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(memberInfoRequest)))
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    @WithMockUser(username = "abcd")
    @DisplayName("registerMemberInfo() : 유저의 정보를 삭제 한다")
    public void deleteMemberInfo() throws Exception{
        mvc.perform(MockMvcRequestBuilders.delete("/member/{memberId}", "abcd"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}