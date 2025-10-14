package com.example.leeseo.domain.member.repository.mapping;

import com.example.leeseo.domain.member.entity.mapping.MemberFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFoodRepository extends JpaRepository<MemberFood, Long> {
}
