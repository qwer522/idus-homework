package com.idus.homework.member.infra.service;

import static org.junit.jupiter.api.Assertions.*;

import com.idus.homework.member.representative.MemberRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberCommandServiceTest {

    @Autowired
    private MemberCommandService memberCommandService;

    @Autowired
    private MemberQueryService memberQueryService;

    @Test
    void 회원_저장을_하고_회원_조회에_성공한다() {
        String email = "jwp920522@gmail.com";
        // given
        final MemberRequest request =
            MemberRequest.builder()
                .email(email)
                .password("Qq1!pppppp")
                .name("jiwon박")
                .phone("01026961397")
                .alias("aliaspark")
                .build();

        // when
        memberCommandService.save(request);
        // then
        assertEquals(memberQueryService.findByEmail(email).getEmail(), email);
    }


}