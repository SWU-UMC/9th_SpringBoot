package com.example.umc9th.domain.inquire.entity;

import com.example.umc9th.global.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "inquire_reply")
public class InquireReply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquire_id", nullable = false)
    private Inquire inquire;

    @NotBlank
    @Size(max = 1000)
    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    public void setInquire(Inquire inquire) { this.inquire = inquire; }

}
