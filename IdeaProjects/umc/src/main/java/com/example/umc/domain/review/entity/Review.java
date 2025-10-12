package com.example.umc.domain.review.entity;

import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.user.entity.User;
import com.example.umc.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name="review")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="mission_id", nullable=false)
    private Mission mission;

    @Lob
    private String content;
    private Integer rating;


    @OneToMany(mappedBy="review", cascade=CascadeType.ALL, orphanRemoval = true)
    @Builder.Default private List<ReviewPhoto> photos = new ArrayList<>();
}

