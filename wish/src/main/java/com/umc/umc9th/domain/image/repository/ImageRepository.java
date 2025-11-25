package com.umc.umc9th.domain.image.repository;

import com.umc.umc9th.domain.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
