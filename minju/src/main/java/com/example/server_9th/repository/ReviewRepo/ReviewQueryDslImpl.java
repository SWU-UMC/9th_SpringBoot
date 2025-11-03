package com.example.server_9th.repository.ReviewRepo;

import com.example.server_9th.domain.QRegion;
import com.example.server_9th.domain.QStore;
import com.example.server_9th.domain.mapping.review.QReview;
import com.example.server_9th.domain.mapping.review.Review;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final EntityManager em;

    //검색 API
    @Override
    public List<Review> searchReview(
            Predicate predicate
    ){
        //JPA 세팅
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        //Q 클래스 선언
        QReview review = QReview.review;
        QStore store = QStore.store;
        QRegion region = QRegion.region;

        return queryFactory
                .selectFrom(review)
                .leftJoin(store).on(store.store_id.eq(review.id.store_id))
                .leftJoin(region).on(region.region_id.eq(store.region.region_id))
                .where(predicate)
                .fetch();
    }
}
