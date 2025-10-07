package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.global.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "store_id", nullable = false)
    private Long storeId;

    @NotNull
    @Min(0)
    @Column(name = "point", nullable = false)
    private Long point;

    @NotNull
    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;

    @NotBlank
    @Size(max = 255)
    @Column(name = "condition", nullable = false, length = 255)
    private String condition;

}
