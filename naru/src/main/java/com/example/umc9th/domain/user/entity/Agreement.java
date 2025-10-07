package com.example.umc9th.domain.user.entity;

import com.example.umc9th.global.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "agreement")
public class Agreement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

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
