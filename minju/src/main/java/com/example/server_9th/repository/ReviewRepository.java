package com.example.server_9th.repository;

import com.example.server_9th.domain.mapping.review.Review;
import com.example.server_9th.domain.mapping.review.ReviewId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, ReviewId> {
    //특정 가게에 등록된 리뷰 전체 조회
    @Query("SELECT r FROM Review r WHERE r.id.store_id = :storeId ORDER BY r.createdAt DESC")
    List<Review> findAllByStoreId(Long storeId);

    //특정 회원의 리뷰 전체 조회
    @Query("SELECT r FROM Review r WHERE r.id.user_id = :userId ORDER BY r.createdAt DESC")
    List<Review> findAllByUserId(Long userId);

    //특정 회원이 특정 가게에 남긴 리뷰 단건 조회 (중복 작성 방지용)
    @Query("SELECT r FROM Review r WHERE r.id.user_id = :userId AND r.id.store_id = :storeId")
    Optional<Review> findByUserIdAndStoreId(Long userId, Long storeId);

}
