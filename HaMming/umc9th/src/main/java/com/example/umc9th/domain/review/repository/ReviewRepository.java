package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    /**
     * 특정 가게(store)에 등록된 리뷰 전체 조회 (임의: 최신순)
     */
    List<Review> findByStoreIdOrderByCreatedAtDesc(Long storeId);

    /**
     * 특정 회원(member)이 작성한 리뷰 전체 조회
     */
    List<Review> findByMemberIdOrderByCreatedAtDesc(Long memberId);

    /**
     * 특정 가게에 대해 리뷰가 존재하는지 확인 (중복 작성 방지용)
     */
    boolean existsByStoreIdAndMemberId(Long storeId, Long memberId);

    /**
     * 가게별 평균 평점을 계산 (집계용)
     */
    @Query("SELECT AVG(r.rate) FROM Review r WHERE r.store.id = :storeId")
    Double findAverageRateByStoreId(@Param("storeId") Long storeId);

    /**
     * 특정 리뷰 내용 검색 (키워드 기반)
     */
    @Query("SELECT r FROM Review r WHERE r.reviewText LIKE %:keyword% ORDER BY r.createdAt DESC")
    List<Review> searchByKeyword(@Param("keyword") String keyword);
}
