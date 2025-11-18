package com.example.umc.domain.member.entity;


import com.example.umc.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="preference")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Preference extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preferenceId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id", nullable = false)
    private Member member;


    @Column(name = "category", length=50, nullable=false)
    private String category;
}
