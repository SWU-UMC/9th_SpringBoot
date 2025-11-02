package com.example.leeseo.domain.review.repository;

import com.example.leeseo.domain.review.dto.QReviewDto;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ReviewQueryDsl {
    List<QReviewDto> searchReview(
            Predicate predicate
    );
}
