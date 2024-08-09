package com.proteinduo.domain.memberManage.api;

import com.proteinduo.domain.memberManage.dto.AddMemberRequest;
import com.proteinduo.domain.memberManage.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>package name   : com.proteinduo.domain.memberManage.api
 * <br>file name      : MemberController
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

@RestController
@RequiredArgsConstructor
public class loginApiController {

    private final MemberService memberService;
    private final UserDetailsService userDetailsService;


    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody AddMemberRequest request) {
        memberService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Signup successful. Please log in.");
    }

    //로그아웃

}
