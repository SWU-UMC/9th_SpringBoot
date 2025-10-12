package com.example.server_9th.domain.mapping.memberTerm;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MemberTermId implements Serializable {
    @Column(name = "term_id")
    private Long term_id;

    @Column(name = "user_id")
    private Long user_id;
}
