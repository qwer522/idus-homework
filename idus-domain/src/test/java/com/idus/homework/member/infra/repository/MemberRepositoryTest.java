package com.idus.homework.member.infra.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.idus.homework.member.domain.Gender;
import com.idus.homework.member.domain.Member;
import com.idus.homework.member.domain.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void 회원_저장을_하고_이메일로_회원조회에_성공한다() {
        // given
        String email = "email1@gmail.com";
        String name = "user1";

        // when
        Member member = memberRepository.findByEmail(email);

        // then
        assertEquals(member.getName(), name);
    }

    @Test
    void 회원_저장을_하고_이메일로_회원_조회에_성공한다() {
        String email = "jwp920522@gmail.com";
        // given
        final Member member = Member.builder()
            .email(email)
            .password("Qq1!pppppp")
            .name("jiwon박")
            .phone("01026961397")
            .alias("aliaspark")
            .gender(Gender.WOMAN)
            .role(Role.USER)
            .build();

        // when
        memberRepository.save(member);
        // then
        assertEquals(memberRepository.findByEmail(email).getEmail(), email);
    }

    @Test
    void 회원아이디로_회원_조회에_성공한다() {
        // given
        Long id = Long.valueOf(1);
        String name = "user1";

        // when
        Member member = memberRepository.findById(id).get();

        // then
        assertEquals(member.getName(), name);
    }
}