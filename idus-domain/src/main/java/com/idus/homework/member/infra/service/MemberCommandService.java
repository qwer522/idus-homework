package com.idus.homework.member.infra.service;

import com.idus.homework.member.domain.Member;
import com.idus.homework.member.domain.Role;
import com.idus.homework.member.infra.repository.MemberRepository;
import com.idus.homework.member.representative.MemberRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberCommandService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    public MemberCommandService(MemberRepository memberRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional(readOnly = true)
    public void save(MemberRequest memberRequest) {
        Member member = requestToMember(memberRequest);
        member.lastLoginDateUpdate();
        memberRepository.save(member);
    }

    private Member requestToMember(MemberRequest memberRequest) {
        return Member.builder()
            .email(memberRequest.getEmail())
            .password(bCryptPasswordEncoder.encode(memberRequest.getPassword()))
            .name(memberRequest.getName())
            .phone(memberRequest.getPhone())
            .alias(memberRequest.getAlias())
            .gender(memberRequest.getGender())
            .role(Role.USER)
            .build();
    }

}