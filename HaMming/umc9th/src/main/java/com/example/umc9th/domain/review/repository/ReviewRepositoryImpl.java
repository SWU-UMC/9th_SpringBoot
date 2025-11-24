package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private final QReview review = QReview.review;

    @Override
    public List<Review> searchReview(BooleanBuilder builder) {
        return queryFactory
                .selectFrom(review)
                .where(builder)
                .orderBy(review.id.desc())
                .fetch();
    }

    @Override
    public List<Review> findMyReviews(BooleanBuilder builder) {
        return queryFactory
                .selectFrom(review)
                .where(builder)
                .orderBy(review.id.desc())
                .fetch();
    }
}
