package com.example.server_9th.repository;

import com.example.server_9th.domain.Member;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Registered
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 이메일로 회원 조회
    // 로그인이나 중복 이메일 체크 시 사용 가능
    Optional<Member> findByEmail(String email);

    //닉네임으로 회원 조회
    Optional<Member> findByName(String name);

    Optional<Member> findById(Long id);

    //특정 역할에 따른 회원 조회
    Optional<Member> findByRole(Enum role);

}
