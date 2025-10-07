package com.example.umc9th.domain.review.entity;

import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.global.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "review")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("5.0")
    @Column(name = "score", nullable = false)
    private Double score;

    @Size(max = 1000)
    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    // 양방향 연관관계
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewPhoto> photos = new ArrayList<>();

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewReply> replies = new ArrayList<>();

    public void addPhoto(ReviewPhoto p) { photos.add(p); p.setReview(this); }
    public void removePhoto(ReviewPhoto p) { photos.remove(p); p.setReview(null); }

    public void addReply(ReviewReply r) { replies.add(r); r.setReview(this); }
    public void removeReply(ReviewReply r) { replies.remove(r); r.setReview(null); }

}
