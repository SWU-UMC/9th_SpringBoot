package com.example.server_9th.service;

import com.example.server_9th.converter.ReviewConverter;
import com.example.server_9th.domain.Member;
import com.example.server_9th.domain.mapping.review.Review;
import com.example.server_9th.dto.ReviewDto;
import com.example.server_9th.repository.ReviewRepo.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewDto.ReviewPreviewListDto getMyReviews(Member member, int page) {

        // 프론트는 1부터 주므로 0-based 로 변환, size = 10 고정
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Review> reviewPage = reviewRepository.findByMember(member, pageable);

        return ReviewConverter.toReviewPreviewListDTO(reviewPage);
    }
}
