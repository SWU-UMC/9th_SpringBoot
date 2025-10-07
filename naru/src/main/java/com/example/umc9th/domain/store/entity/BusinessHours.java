package com.example.umc9th.domain.store.entity;

import com.example.umc9th.domain.store.entity.enums.Days;
import com.example.umc9th.global.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalTime;

@Entity
public class BusinessHours extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long storeId;

    private Days days;

    private LocalTime startTime;

    private LocalTime endTime;

}
