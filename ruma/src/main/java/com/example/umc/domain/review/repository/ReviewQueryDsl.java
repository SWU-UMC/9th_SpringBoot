package com.example.umc.domain.review.repository;

import com.example.umc.domain.review.dto.QReviewDto;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ReviewQueryDsl {
    default List<QReviewDto> findMyReviews(Predicate predicate) {
        return findMyReviews(predicate, null);
    }

    List<QReviewDto> findMyReviews(Predicate predicate, Long cursorId);

    List<QReviewDto> findMemberReviews(Long memberId, int page, int pageSize);
    long countMemberReviews(Long memberId);

}

