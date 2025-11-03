package com.example.leeseo.domain.review.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class QReviewDto {
    private Long id;
    private LocalDateTime created_at;
    private String content;
    private float rate;
    private String store_name;
    private String member_name;

    public QReviewDto(Long id, LocalDateTime created_at, String content, float rate, String store_name, String member_name) {
        this.id = id;
        this.created_at = created_at;
        this.content = content;
        this.rate = rate;
        this.store_name = store_name;
        this.member_name = member_name;
    }
}
