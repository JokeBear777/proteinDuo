package com.proteinduo.domain.memberManage.service;

import com.proteinduo.domain.memberManage.dto.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <br>package name   : com.proteinduo.domain.memberManage.service
 * <br>file name      : MemberServiceImpl
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

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
}
