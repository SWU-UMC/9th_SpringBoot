package com.example.server_9th.repository.ReviewRepo;

import com.example.server_9th.domain.mapping.review.Review;
import com.querydsl.core.types.Predicate;


import java.util.List;


public interface ReviewQueryDsl {
    //검색 API
    List<Review> searchReview(Predicate predicate);

    // 내가 작성한 리뷰 조회 (가게명 + 별점)
    List<Review> searchMyReviews(Predicate predicate);
}
