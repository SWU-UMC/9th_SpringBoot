package com.example.leeseo.domain.member.repository.mapping;

import com.example.leeseo.domain.member.entity.mapping.MemberTerm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTermRepository extends JpaRepository<MemberTerm, Long> {
}
