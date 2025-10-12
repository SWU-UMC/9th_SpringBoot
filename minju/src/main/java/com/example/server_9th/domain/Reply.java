package com.example.server_9th.domain;

import com.example.server_9th.domain.common.BaseEntity;
import com.example.server_9th.domain.mapping.review.Review;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reply_id;

    @Column(nullable = false, length = 500)
    private String content;

    @OneToOne(mappedBy = "reply")
    private Review review;
}
