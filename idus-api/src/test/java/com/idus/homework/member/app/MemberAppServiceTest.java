package com.idus.homework.member.app;

import static org.junit.jupiter.api.Assertions.*;

import com.idus.homework.common.exception.MemberNotFoundException;
import com.idus.homework.member.representation.MemberDetailResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberAppServiceTest {

    @Autowired
    private MemberAppService memberAppService;

    @Test
    void 회원아이디로_조회시_정상적으로_조회가_되어야한다() throws MemberNotFoundException {
        // given
        Long id = Long.valueOf(1);
        String email = "email1@gmail.com";

        // when
        MemberDetailResponse response = memberAppService.findById(id);

        // then
        assertEquals(response.getEmail(), email);
    }
}