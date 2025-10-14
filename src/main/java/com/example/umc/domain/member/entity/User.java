package com.example.umc.domain.member.entity;

import com.example.umc.domain.member.enums.Gender;
import com.example.umc.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name ="name" , length = 50, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name="gender", nullable = false)
    private Gender gender;

    private LocalDate birthday;

    @Column(name ="address", length = 100)
    private String address;

    @Column(name = "detail_address")
    private String addressDetail;

    @Column(name = "point", nullable = false)
    @Builder.Default
    private Long point = 0L;
}
