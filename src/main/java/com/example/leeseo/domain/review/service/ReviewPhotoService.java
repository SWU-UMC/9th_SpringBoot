package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.entity.ReviewPhoto;
import com.example.umc9th.domain.review.repository.ReviewPhotoRepository;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewPhotoService {
    private final ReviewRepository reviewRepository;
    private final ReviewPhotoRepository reviewPhotoRepository;

    public ReviewPhotoService(ReviewRepository reviewRepository, ReviewPhotoRepository reviewPhotoRepository) {
        this.reviewRepository = reviewRepository;
        this.reviewPhotoRepository = reviewPhotoRepository;
    }

    public ReviewPhoto insert(Long review_id, String photo_url){
        Review review = reviewRepository.getReferenceById(review_id);

        ReviewPhoto reviewPhoto = ReviewPhoto.builder()
                .review(review)
                .photo_url(photo_url)
                .build();

        return reviewPhotoRepository.save(reviewPhoto);
    }
}
