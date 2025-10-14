package com.example.leeseo.domain.store.repository;

import com.example.leeseo.domain.store.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
