package com.example.umc.domain.terms.entity;

import com.example.umc.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity @Table(name="terms")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Terms extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long termsId;

    @Column(name = "version", length=20, nullable=false)
    private String version;
    @Lob
    @Column(name = "content", nullable=false)
    private String content;

    @OneToMany(mappedBy = "terms")
    private List<UserTerms> userTerms;
}
