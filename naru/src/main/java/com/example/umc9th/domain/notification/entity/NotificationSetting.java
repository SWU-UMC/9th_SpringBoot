package com.example.umc9th.domain.notification.entity;

import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.global.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "notification_setting")
public class NotificationSetting extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @NotNull
    @Column(name = "new_event", nullable = false)
    private Boolean newEvent;

    @NotNull
    @Column(name = "review_reply", nullable = false)
    private Boolean reviewReply;

    @NotNull
    @Column(name = "inquire_reply", nullable = false)
    private Boolean inquireReply;

}
