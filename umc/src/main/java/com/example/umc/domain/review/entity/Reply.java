package com.example.umc.domain.review.entity;

import com.example.umc.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="reply")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="review_id", nullable=false)
    private Review review;

    @Lob
    private String content;

}

