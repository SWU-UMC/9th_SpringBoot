package com.example.leeseo.domain.review.repository;

import com.example.leeseo.domain.review.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
