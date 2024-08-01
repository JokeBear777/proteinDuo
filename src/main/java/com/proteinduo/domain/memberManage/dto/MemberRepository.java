package com.proteinduo.domain.memberManage.dto;

import com.proteinduo.domain.memberManage.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <br>package name   : com.proteinduo.domain.memberManage.dto
 * <br>file name      : MemberRepository
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
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    //
}
