package com.example.server_9th.domain.mapping.memberTerm;

import com.example.server_9th.domain.Member;
import com.example.server_9th.domain.Term;
import com.example.server_9th.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberTerm {
    @EmbeddedId
    private MemberTermId memberTermId;

    private String isAgreed;

    private LocalDateTime agreedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("term_id")
    @JoinColumn(name = "term_id")
    private Term term;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private Member member;


}
