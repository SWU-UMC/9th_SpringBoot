package com.example.leeseo.domain.store.repository;

import com.example.leeseo.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
