package com.example.leeseo.domain.member.repository;

import com.example.leeseo.domain.member.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Term, Long> {
}
