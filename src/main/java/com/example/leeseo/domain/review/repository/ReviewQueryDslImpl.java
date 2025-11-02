package com.example.leeseo.domain.review.repository;

import com.example.leeseo.domain.review.entity.QReview;
import com.example.leeseo.domain.review.entity.Review;
import com.example.leeseo.domain.store.entity.QLocation;
import com.example.leeseo.domain.store.entity.QStore;
import com.querydsl.core.types.Predicate;
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
    public List<Review> searchReview(Predicate predicate) {
        //JPA 세팅
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        //Q클래스 선언
        QReview review = QReview.review;
        QStore store = QStore.store;
        QLocation location = QLocation.location;

        return queryFactory
                .selectFrom(review)
                .leftJoin(store).on(store.id.eq(review.store.id))
                .leftJoin(location).on(location.id.eq(store.location.id))
                .where(predicate)
                .fetch();
    }
}
