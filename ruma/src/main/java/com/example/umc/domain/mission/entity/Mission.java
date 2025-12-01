package com.example.umc.domain.mission.entity;

import com.example.umc.domain.mission.enums.ProofType;
import com.example.umc.domain.store.entity.Store;
import com.example.umc.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="mission")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long missionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id", nullable=false)
    private Store store;

    @Column(name = "title", length=100, nullable=false)
    private String title;
    @Lob
    private String description;
    private Integer point;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Enumerated(EnumType.STRING)
    private ProofType proofType;
}
