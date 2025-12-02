package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.member.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "user")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column
    private String email;

    @Column(length = 100)
    private String password;

    @Column(length = 30)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column( length = 20)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column
    private Gender gender;


    @Column(length = 10)
    private String birth;


    private LocalDateTime createdAt;

    //카카오 로그인
    @Column(unique = true)
    private String kakaoId;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.role == null) this.role = Role.ROLE_USER;
    }
}
