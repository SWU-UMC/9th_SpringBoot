package com.example.server_9th.domain;

import com.example.server_9th.domain.common.BaseEntity;
import com.example.server_9th.domain.enums.Gender;
import com.example.server_9th.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 3)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(nullable = false)
    private LocalDateTime birth;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String address;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Role role = Role.CUSTOMER;









}
