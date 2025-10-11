package com.example.umc9th.domain.notification.entity;

import com.example.umc9th.domain.notification.entity.enums.NotificationCategory;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.global.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "notification")
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotBlank
    @Size(max = 500)
    @Column(name = "content", nullable = false, length = 500)
    private String content;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false, length = 50)
    private NotificationCategory category;

    @Builder.Default
    @NotNull
    @Column(name = "is_read", nullable = false)
    private Boolean isRead = false;

}
