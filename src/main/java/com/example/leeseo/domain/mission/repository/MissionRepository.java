package com.example.leeseo.domain.mission.repository;

import com.example.leeseo.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
