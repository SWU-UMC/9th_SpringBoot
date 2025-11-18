package com.example.leeseo.domain.review.repository;

import com.example.leeseo.domain.review.dto.QReviewDto;
import com.querydsl.core.types.Predicate;
import java.util.List;

public interface ReviewQueryDsl {

    default List<QReviewDto> searchReview(
            Predicate predicate
    ){
        return searchReview(predicate,null);
    };

    List<QReviewDto> searchReview(
            Predicate predicate,
            Long cursorId
    );
}
