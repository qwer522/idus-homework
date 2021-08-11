package com.idus.homework.member.infra.service;

import com.idus.homework.member.domain.Member;
import com.idus.homework.member.infra.repository.MemberRepository;
import com.idus.homework.member.representative.MemberSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MemberQueryService {

    private final MemberRepository memberRepository;

    public MemberQueryService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = true)
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public Optional<Member> findById(long id) {
        return memberRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<Member> findAllByCondition(MemberSearchCondition memberSearchCondition, Pageable pageable) {
        return memberRepository.findAllByCondition(memberSearchCondition, pageable);
    }

}