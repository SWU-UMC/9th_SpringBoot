package com.example.server_9th.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long term_id;

    @Column(nullable = false)
    private String termName;

    @Builder.Default
    private Boolean isRequired = false;
}
