package com.example.umc9th.domain.inquire.entity;

import com.example.umc9th.domain.inquire.entity.enums.InquireStatus;
import com.example.umc9th.domain.inquire.entity.enums.InquireType;
import com.example.umc9th.global.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Inquire extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String content;

    private InquireType type;

    private InquireStatus status;

}
