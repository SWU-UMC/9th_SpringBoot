package com.example.umc9th.domain.user.entity;

import com.example.umc9th.global.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Agreement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 나중에 @ManyToOne(User)로 교체 예정
    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @NotNull
    @Column(name = "age_consent", nullable = false)
    private Boolean ageConsent;

    @NotNull
    @Column(name = "privacy_consent", nullable = false)
    private Boolean privacyConsent;

    @NotNull
    @Column(name = "location_consent", nullable = false)
    private Boolean locationConsent;

    @NotNull
    @Column(name = "marketing_consent", nullable = false)
    private Boolean marketingConsent;

}
