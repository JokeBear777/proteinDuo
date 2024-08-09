package com.proteinduo.domain.memberManage.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proteinduo.domain.memberManage.dto.AddMemberRequest;
import com.proteinduo.domain.memberManage.dto.MemberInfoRequest;
import com.proteinduo.domain.memberManage.service.MemberService;
import com.proteinduo.domain.memberManage.service.UserDetailService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

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

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static MvcResult loginResult;

    @BeforeEach
    public void signup() throws Exception {
        //가상 회원가입
        AddMemberRequest signupRequest = new AddMemberRequest();
        signupRequest.setMemberId("12345");
        signupRequest.setPassword("password");

        Mockito.when(memberService.save(signupRequest)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/member/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signupRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Signup successful. Please log in."));

        // 로그인
        loginResult = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .param("username", "12345")
                        .param("password", "password"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andReturn();


    }




    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private UserDetailService userDetailSErvice;

    @Test
    public void testGetMemberInfo() throws Exception{
        // Given
        String memberId = "12345";
        MemberInfoRequest memberInfoRequest = new MemberInfoRequest();
        memberInfoRequest.setUsername("protein");
        memberInfoRequest.setEmail("protein@example.com");

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/member/{memberId}", memberId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(memberInfoRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(memberService).memberInfoSave(Mockito.eq(memberId), Mockito.any(MemberInfoRequest.class));
    }


    @Test
    void registerMemberInfo() {
    }

    @Test
    void deleteMemberInfo() {
    }
}