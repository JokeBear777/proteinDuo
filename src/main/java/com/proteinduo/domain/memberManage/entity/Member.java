package com.proteinduo.domain.memberManage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * <br>package name   : com.proteinduo.domain.memberManage.entity
 * <br>file name      : Member
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
@Entity
public class Member {
    @Id
    private Long id;

    private String username;
    private String password;

}
