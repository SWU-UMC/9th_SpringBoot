package com.example.umc.domain.notification.entity;

import com.example.umc.domain.member.entity.Member;
import com.example.umc.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notification")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id", nullable = false)
    private Member member;  // 알림을 받은 사용자


    @Column(name = "notification_category", length = 50, nullable = false)
    private String category;

    @Column(name = "message")
    private String message;

    @Column(name = "is_read", nullable = false)
    private boolean isRead;

    // 알림의 상태 (읽음/안 읽음) 여부를 처리하는 메서드
    public void markAsRead() {
        this.isRead = true;
    }
}


