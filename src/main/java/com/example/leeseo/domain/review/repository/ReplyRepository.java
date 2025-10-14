package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
