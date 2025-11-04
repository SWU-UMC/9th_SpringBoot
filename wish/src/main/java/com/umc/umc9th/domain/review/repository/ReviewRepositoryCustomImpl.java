package com.umc.umc9th.domain.review.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.umc9th.domain.review.dto.MyReviewResponse;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.util.List;

import static com.umc.umc9th.domain.review.entity.QReview.review;
import static com.umc.umc9th.domain.review.entity.QReviewReply.reviewReply;
import static com.umc.umc9th.domain.store.entity.QStore.store;
import static com.umc.umc9th.domain.user.entity.QUser.user;

public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  public ReviewRepositoryCustomImpl(EntityManager em) {
    this.queryFactory = new JPAQueryFactory(em);
  }

  @Override
  public Page<MyReviewResponse> findMyReviews(Integer userId, Integer storeId,
      BigDecimal minRating, BigDecimal maxRating,
      Pageable pageable) {

    List<MyReviewResponse> content = queryFactory
        .select(Projections.constructor(MyReviewResponse.class,
            review.id, user.name, review.rating, review.content, review.createdAt,
            store.id, store.storeName, reviewReply.content, reviewReply.createdAt))
        .from(review)
        .join(review.user, user)
        .join(review.store, store)
        .leftJoin(reviewReply).on(reviewReply.review.eq(review))
        .where(review.user.id.eq(userId), storeIdEq(storeId), ratingGoe(minRating), ratingLoe(maxRating))
        .orderBy(review.createdAt.desc())
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    long total = queryFactory
        .select(review.count())
        .from(review)
        .where(review.user.id.eq(userId), storeIdEq(storeId), ratingGoe(minRating), ratingLoe(maxRating))
        .fetchOne();

    return new PageImpl<>(content, pageable, total);
  }

  private BooleanExpression storeIdEq(Integer storeId) {
    return storeId != null ? review.store.id.eq(storeId) : null;
  }

  private BooleanExpression ratingGoe(BigDecimal minRating) {
    return minRating != null ? review.rating.goe(minRating) : null;
  }

  private BooleanExpression ratingLoe(BigDecimal maxRating) {
    return maxRating != null ? review.rating.loe(maxRating) : null;
  }
}