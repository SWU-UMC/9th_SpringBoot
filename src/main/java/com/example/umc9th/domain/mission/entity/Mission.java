package com.example.umc9th.domain.mission.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @Column(name = "conditional", nullable = false)
    private String conditional;

    @Column(name = "point", nullable = false)
    private Integer point;
}
