package com.example.server_9th.repository.ReviewRepo;

import com.example.server_9th.domain.mapping.review.Review;
import com.querydsl.core.types.Predicate;


import java.util.List;


public interface ReviewQueryDsl {
    //검색 API
    List<Review> searchReview(
            Predicate predicate
    );
}
