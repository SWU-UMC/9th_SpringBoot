package com.example.umc9th.domain.notification.entity;

import com.example.umc9th.domain.notification.entity.enums.NotificationCategory;
import com.example.umc9th.global.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String content;

    private NotificationCategory category;

    private Boolean isRead;

}
