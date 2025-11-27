package com.example.umc.domain.review.repository;

import com.example.umc.domain.mission.entity.QMission;
import com.example.umc.domain.review.dto.QReviewDto;
import com.example.umc.domain.review.entity.QReview;
import com.example.umc.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final EntityManager em;
    private static final int PAGE_SIZE = 5;

    @Override
    public List<QReviewDto> findMyReviews(Predicate predicate, Long cursorId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QReview review = QReview.review;
        QMission mission = QMission.mission;
        QStore store = QStore.store;

        BooleanBuilder whereCondition = new BooleanBuilder(predicate);

        if (cursorId != null && cursorId > 0) {
            whereCondition.and(review.reviewId.lt(cursorId));
        }

        return queryFactory
                .select(Projections.constructor(
                        QReviewDto.class,
                        review.reviewId,
                        review.createdAt,
                        review.content,
                        review.rating,
                        store.name,
                        review.member.name,
                        review.member.memberId
                ))
                .from(review)
                .leftJoin(review.mission, mission)
                .leftJoin(mission.store, store)
                .where(whereCondition)
                .orderBy(review.reviewId.desc())
                .limit(PAGE_SIZE)
                .fetch();
    }
    // 가게명으로 검색
    public List<QReviewDto> findByStoreName(String query) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QReview review = QReview.review;
        QMission mission = QMission.mission;
        QStore store = QStore.store;

        return queryFactory
                .select(Projections.constructor(
                        QReviewDto.class,
                        review.reviewId,
                        review.createdAt,
                        review.content,
                        review.rating,
                        store.name,
                        review.member.name,
                        review.member.memberId
                ))
                .from(review)
                .leftJoin(review.mission, mission)
                .leftJoin(mission.store, store)
                .where(store.name.containsIgnoreCase(query))
                .orderBy(review.reviewId.desc())
                .fetch();
    }

    // 별점으로 검색
    public List<QReviewDto> findByRating(Integer rate) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QReview review = QReview.review;
        QMission mission = QMission.mission;
        QStore store = QStore.store;

        return queryFactory
                .select(Projections.constructor(
                        QReviewDto.class,
                        review.reviewId,
                        review.createdAt,
                        review.content,
                        review.rating,
                        store.name,
                        review.member.name,
                        review.member.memberId
                ))
                .from(review)
                .leftJoin(review.mission, mission)
                .leftJoin(mission.store, store)
                .where(review.rating.eq(rate))
                .orderBy(review.reviewId.desc())
                .fetch();
    }

    // 가게명 + 별점 통합검색
    public List<QReviewDto> findByStoreOrRating(String query) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QReview review = QReview.review;
        QMission mission = QMission.mission;
        QStore store = QStore.store;

        BooleanBuilder whereCondition = new BooleanBuilder();
        whereCondition.or(store.name.containsIgnoreCase(query));

        try {
            int rate = Integer.parseInt(query);
            whereCondition.or(review.rating.eq(rate));
        } catch (NumberFormatException ignored) {}

        return queryFactory
                .select(Projections.constructor(
                        QReviewDto.class,
                        review.reviewId,
                        review.createdAt,
                        review.content,
                        review.rating,
                        store.name,
                        review.member.name,
                        review.member.memberId
                ))
                .from(review)
                .leftJoin(review.mission, mission)
                .leftJoin(mission.store, store)
                .where(whereCondition)
                .orderBy(review.reviewId.desc())
                .fetch();
    }
    //특정 사용자의 리뷰 리스트 반환
    public List<QReviewDto> findMemberReviews(Long memberId, int page, int pageSize) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QReview review = QReview.review;
        QMission mission = QMission.mission;
        QStore store = QStore.store;

        return queryFactory
                .select(Projections.constructor(
                        QReviewDto.class,
                        review.reviewId,
                        review.createdAt,
                        review.content,
                        review.rating,
                        store.name,
                        review.member.name,
                        review.member.memberId
                ))
                .from(review)
                .leftJoin(review.mission, mission)
                .leftJoin(mission.store, store)
                .where(review.member.memberId.eq(memberId))
                .orderBy(review.reviewId.desc())
                .offset((long) page * pageSize)
                .limit(pageSize)
                .fetch();
    }



    public long countMemberReviews(Long memberId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QReview review = QReview.review;

        return queryFactory
                .select(review.count())
                .from(review)
                .where(review.member.memberId.eq(memberId))
                .fetchOne();
    }
}
