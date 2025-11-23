package com.example.leeseo.domain.review.repository;

import com.example.leeseo.domain.member.entity.Member;
import com.example.leeseo.domain.review.entity.Review;
import com.example.leeseo.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {
    Page<Review> findAllByStore(Store store, PageRequest pageRequest);

    Page<Review> findALlByMember(Member member, PageRequest pageRequest);
}
