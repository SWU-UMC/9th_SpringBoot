package com.example.umc.domain.review.entity;

import com.example.umc.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="review_photo")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ReviewPhoto extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewPhotoId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="review_id", nullable=false)
    private Review review;

    @Column(name = "photo_url", length=255, nullable=false)
    private String photoUrl;
}

