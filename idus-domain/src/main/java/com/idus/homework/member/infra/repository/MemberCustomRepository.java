package com.idus.homework.member.infra.repository;


import com.idus.homework.member.domain.Member;
import com.idus.homework.member.representative.MemberSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberCustomRepository {
    Page<Member> findAllByCondition(MemberSearchCondition memberSearchCondition, Pageable pageable);
}
