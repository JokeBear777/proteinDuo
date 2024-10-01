package com.proteinduo.domain.memberManage.configure;

import com.proteinduo.domain.memberManage.dto.AddMemberRequestDto;
import com.proteinduo.domain.memberManage.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * packageName    : com.proteinduo.domain.memberManage.configure
 * fileName       : SecurityConfigTest
 * author         : 82102
 * date           : 2024-08-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-09        82102       최초 생성
 */

@SpringBootTest
public class SecurityConfigTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private MemberService memberService;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .apply(springSecurity())
                .build();

        AddMemberRequestDto addMemberRequestDto = new AddMemberRequestDto("abcd", "1234");
        memberService.save(addMemberRequestDto);
    }

    @Test
    @DisplayName("Login 테스트")
    public void login_test() throws Exception {
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


}
