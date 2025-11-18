package com.example.leeseo.domain.member.repository;

import com.example.leeseo.domain.member.dto.MyPageDto;
import com.example.leeseo.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT new com.example.leeseo.domain.member.dto.MyPageDto(m.id, m.email, m.phone_number, m.point) " +
            "FROM Member m WHERE m.id = :id")
    Optional<MyPageDto> findCurrentMember(@Param("id") Long id);
}