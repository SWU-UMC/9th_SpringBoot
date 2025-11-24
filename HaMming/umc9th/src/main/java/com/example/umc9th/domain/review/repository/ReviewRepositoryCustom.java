package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.BooleanBuilder;

import java.util.List;

public interface ReviewRepositoryCustom {

    List<Review> searchReview(BooleanBuilder builder);

    List<Review> findMyReviews(BooleanBuilder builder);
}
