package com.proteinduo.domain.memberManage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
@Table(name = "users")
@Getter
@NoArgsConstructor
public class Users {

    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "height", nullable = false)
    private Integer height;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @Column(name = "muscle_mass", nullable = true) //골격근량
    private Integer muscleMass;

    @Column(name = "bodyFat", nullable = true) //체지방량
    private Integer bodyFat;

    @Column(name = "bmi", nullable = false)
    private Integer bmi;

    @Column(name = "body_fat_percentage", nullable = true) //체지방률
    private Integer bodyFatPercentage;

    @Builder
    public Users(String userId, String password, String username, String email, boolean enabled, String gender,
                 Integer height, Integer weight, Integer muscleMass, Integer bodyFat,
                 int bmi, int bodyFatPercentage) {
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.email = email;
        this.enabled = enabled;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.muscleMass = muscleMass;
        this.bodyFat = bodyFat;
        this.bmi = bmi;
        this.bodyFatPercentage = bodyFatPercentage;
    }



}
