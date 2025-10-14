package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.ReviewPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ReviewPhotoRepository extends JpaRepository<ReviewPhoto, Long> {

    // 사진 있는 리뷰

    // 메서드 이름 기반 쿼리
    // save()는 JpaRepository 기본 제공

    // @Query 기반 쿼리 (명시적 INSERT)
    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO review_photo (review_id, image_url, created_at, updated_at)
        VALUES (:reviewId, :imageUrl, NOW(), NOW())
    """, nativeQuery = true)
    void insertPhoto(
            @Param("reviewId") Long reviewId,
            @Param("imageUrl") String imageUrl
    );
}
