package com.example.umc.domain.terms.entity;

import com.example.umc.domain.user.entity.User;
import com.example.umc.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="user_terms")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UserTerms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="terms_id", nullable=false)
    private Terms terms;

    @CreationTimestamp
    private LocalDateTime agreedAt;
}

