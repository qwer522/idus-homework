package com.idus.homework.member.infra.repository;


import com.idus.homework.member.domain.Member;
import com.idus.homework.member.representative.MemberSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.idus.homework.member.domain.QMember.member;

import java.util.List;

@Repository
public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberCustomRepository {

    public MemberRepositoryImpl() {
        super(Member.class);
    }

    @Override
    public Page<Member> findAllByCondition(MemberSearchCondition memberSearchCondition, Pageable pageable) {
        long totalCount = from(member)
            .where(memberSearchCondition.getPredicate())
            .fetchCount();
        List<Member> members = from(member)
            .where(memberSearchCondition.getPredicate())
            .orderBy(member.id.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();
        return new PageImpl<>(members, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), totalCount);
    }
}
