package com.example.umc9th.domain.user.entity;

import com.example.umc9th.domain.user.entity.enums.Gender;
import com.example.umc9th.domain.user.entity.enums.SocialType;
import com.example.umc9th.domain.user.entity.enums.UserRole;
import com.example.umc9th.domain.user.entity.enums.UserStatus;
import com.example.umc9th.global.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 소셜로그인 없을 수도 있으니 null 허용
    @Column(name = "social_id")
    private Long socialId;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", length = 30)
    private SocialType socialType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 30)
    private UserRole role;

    @Email
    @Size(max = 255)
    @Column(name = "email", length = 255, unique = true)
    private String email;

    // 해시 기준으로 길이 여유
    @Size(max = 100)
    @Column(name = "password", length = 100)
    private String password;

    @NotBlank
    @Size(max = 50)
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Size(max = 30)
    @Column(name = "gender", length = 30)
    private Gender gender;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Size(max = 20)
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Size(max = 255)
    @Column(name = "address", length = 255)
    private String address;

    @Size(max = 255)
    @Column(name = "detail_address", length = 255)
    private String detailAddress;

    @Min(0)
    @Column(name = "point", nullable = false)
    private Long point;

    @Size(max = 512)
    @Column(name = "image_url", length = 512)
    private String imageUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private UserStatus status = UserStatus.ACTIVE;

    @Column(name = "inactive_at")
    private LocalDateTime inactiveAt;

}
