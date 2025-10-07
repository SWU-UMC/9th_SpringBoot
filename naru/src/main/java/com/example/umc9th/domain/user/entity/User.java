package com.example.umc9th.domain.user.entity;

import com.example.umc9th.domain.user.entity.enums.Gender;
import com.example.umc9th.domain.user.entity.enums.SocialType;
import com.example.umc9th.domain.user.entity.enums.UserRole;
import com.example.umc9th.domain.user.entity.enums.UserStatus;
import com.example.umc9th.global.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long socialId;

    private SocialType socialType;

    private UserRole role;

    private String email;

    private String password;

    private String name;

    private Gender gender;

    private LocalDate birthday;

    private String phoneNumber;

    private String address;

    private String detailAddress;

    private Long point;

    private String imageUrl;

    private UserStatus status;

    private LocalDateTime inactiveAt;

}
