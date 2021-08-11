package com.idus.homework.member.representative;

import static com.idus.homework.member.domain.QMember.member;

import com.querydsl.core.BooleanBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
@AllArgsConstructor
public class MemberSearchCondition {

    private String name;
    private String email;

    public BooleanBuilder getPredicate() {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        nameLike(booleanBuilder);
        emailLike(booleanBuilder);
        return booleanBuilder;
    }

    private void nameLike(BooleanBuilder booleanBuilder) {
        if (StringUtils.hasText(name)) {
            booleanBuilder.and(member.name.contains(name));
        }
    }

    private void emailLike(BooleanBuilder booleanBuilder) {
        if (StringUtils.hasText(email)) {
            booleanBuilder.and(member.email.contains(email));
        }
    }
}