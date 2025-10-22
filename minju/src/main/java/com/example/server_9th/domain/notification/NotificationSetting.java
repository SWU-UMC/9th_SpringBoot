package com.example.server_9th.domain.notification;

import com.example.server_9th.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class NotificationSetting {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Builder.Default
    @Column(name = "receive_event", nullable = false)
    private boolean receiveEvent = true;

    @Builder.Default
    @Column(name = "receive_review_reply", nullable = false)
    private boolean receiveReviewReply=true;

    @Builder.Default
    @Column(name = "receive_inquiry_reply", nullable = false)
    private boolean receiveInquiryReply=true;

    @OneToOne
    @MapsId // userId가 PK이자 FK임을 명시
    @JoinColumn(name = "user_id")
    private Member member;
}
