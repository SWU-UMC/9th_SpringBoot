package com.example.leeseo.domain.review.repository;

import com.example.leeseo.domain.member.entity.QMember;
import com.example.leeseo.domain.review.dto.QReviewDto;
import com.example.leeseo.domain.review.entity.QReview;
import com.example.leeseo.domain.review.entity.Review;
import com.example.leeseo.domain.store.entity.QLocation;
import com.example.leeseo.domain.store.entity.QStore;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl{
    private final EntityManager em;

    @Override
    public List<QReviewDto> searchReview(Predicate predicate) {
        //JPA 세팅
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        //Q클래스 선언
        QReview review = QReview.review;
        QStore store = QStore.store;
        QLocation location = QLocation.location;
        QMember member = QMember.member;

        return queryFactory
                .select(Projections.constructor(
                        QReviewDto.class,
                        review.id,
                        review.created_at,
                        review.content,
                        review.rate.floatValue(),
                        store.name,
                        review.member.name
                ))
                .from(review)
                .leftJoin(review.store, store)
                .leftJoin(store.location, location)
                .where(predicate)
                .fetch();
    }
}
