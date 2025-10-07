package com.example.umc9th.domain.notification.entity;

import com.example.umc9th.global.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class NotificationSetting extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

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
