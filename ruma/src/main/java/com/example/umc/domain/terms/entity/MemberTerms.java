package com.example.umc.domain.terms.entity;

import com.example.umc.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="member_terms")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MemberTerms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberTermId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terms_id", referencedColumnName = "termsId", nullable = false)
    private Terms terms;

    @CreationTimestamp
    @Column(name = "agreed_at", nullable = false)
    private LocalDateTime agreedAt;
}

