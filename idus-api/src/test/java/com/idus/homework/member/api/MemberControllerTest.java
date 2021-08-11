package com.idus.homework.member.api;

import static org.junit.jupiter.api.Assertions.*;

import com.google.gson.Gson;
import com.idus.homework.member.app.MemberAppService;
import com.idus.homework.member.infra.service.MemberCommandService;
import com.idus.homework.member.representative.MemberRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberAppService memberAppService;

    private Gson gson;

    @BeforeEach
    public void init() {
        gson = new Gson();
    }

    @DisplayName("사용자 추가 실패 - 이메일 형식이 아님")
    @Test
    void 이메일_형식이_아니면_CODE_4061을_반환한다() throws Exception {
        // given
        final MemberRequest request =
            MemberRequest.builder()
                .email("jwp920522@@gmail.com")
                .password("Qq1!pppppp")
                .name("jiwon박")
                .phone("01026961397")
                .alias("aliaspark")
                .build();

        // when
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
            .post("/v1/member")
            .content(gson.toJson(request))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        // then
        assertTrue(contentAsString.contains("\"code\":\"4061\""));
    }

    @DisplayName("사용자 추가 실패 - 비밀번호 형식이 아님")
    @RepeatedTest(4)
    void 비밀번호_영문_대문자_소문자_특수문자_숫자_각_1개_이상씩_포함이_아니면_CODE_4061을_반환한다(RepetitionInfo info) throws Exception {
        // given
        String password = "";
        if (info.getCurrentRepetition() == 1) {
            password = "q1!pppppp";
        } else if (info.getCurrentRepetition() == 2) {
            password = "Qq!pppppp";
        } else if (info.getCurrentRepetition() == 3) {
            password = "Qq1pppppp";
        }
        final MemberRequest request =
            MemberRequest.builder()
                .email("jwp920522@gmail.com")
                .password(password)
                .name("jiwon박")
                .phone("01026961397")
                .alias("aliaspark")
                .build();

        // when
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
            .post("/v1/member")
            .content(gson.toJson(request))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        // then
        assertTrue(contentAsString.contains("\"code\":\"4061\""));
    }

    @DisplayName("사용자 추가 실패 - 이름 형식이 아님")
    @RepeatedTest(3)
    void 이름은_한글_영문_대소문자를_제외한_다른_문자가오면_CODE_4061을_반환한다(RepetitionInfo info) throws Exception {
        // given
        String name = "";
        if (info.getCurrentRepetition() == 1) {
            name = "q1!pppppp";
        } else if (info.getCurrentRepetition() == 2) {
            name = "Qq1pppppp";
        }
        final MemberRequest request =
            MemberRequest.builder()
                .email("jwp920522@gmail.com")
                .password("Qq1!pppppp")
                .name(name)
                .phone("01026961397")
                .alias("aliaspark")
                .build();

        // when
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
            .post("/v1/member")
            .content(gson.toJson(request))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        // then
        assertTrue(contentAsString.contains("\"code\":\"4061\""));
    }

    @DisplayName("사용자 추가 실패 - 별칭 형식이 아님")
    @RepeatedTest(4)
    void 별칭은_영문_소문자를_제외한_다른_문자가오면_CODE_4061을_반환한다(RepetitionInfo info) throws Exception {
        // given
        String alias = "";
        if (info.getCurrentRepetition() == 1) {
            alias = "aliasPark";
        } else if (info.getCurrentRepetition() == 2) {
            alias = "aliaspark1";
        } else if (info.getCurrentRepetition() == 3) {
            alias = "aliaspark!";
        }
        final MemberRequest request =
            MemberRequest.builder()
                .email("jwp920522@gmail.com")
                .password("Qq1!pppppp")
                .name("jiwon박")
                .phone("01026961397")
                .alias(alias)
                .build();

        // when
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
            .post("/v1/member")
            .content(gson.toJson(request))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        // then
        assertTrue(contentAsString.contains("\"code\":\"4061\""));
    }

    @DisplayName("사용자 추가 실패 - 전화번호 형식이 아님")
    @RepeatedTest(5)
    void 전화번호_숫자를_제외한_다른_문자가오면_CODE_4061을_반환한다(RepetitionInfo info) throws Exception {
        // given
        String phone = "";
        if (info.getCurrentRepetition() == 1) {
            phone = "010-26961397";
        } else if (info.getCurrentRepetition() == 2) {
            phone = "010!26961397";
        } else if (info.getCurrentRepetition() == 3) {
            phone = "010a26961397";
        } else if (info.getCurrentRepetition() == 4) {
            phone = "010ㅁ26961397";
        }
        final MemberRequest request =
            MemberRequest.builder()
                .email("jwp920522@gmail.com")
                .password("Qq1!pppppp")
                .name("jiwon박")
                .phone(phone)
                .alias("aliaspark")
                .build();

        // when
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
            .post("/v1/member")
            .content(gson.toJson(request))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        // then
        assertTrue(contentAsString.contains("\"code\":\"4061\""));
    }

    @DisplayName("사용자 추가 성공")
    @Test
    void 모든형식이_맞으면_사용자_추가에_성공한다() throws Exception {
        // given
        final MemberRequest request =
            MemberRequest.builder()
                .email("jwp920522@gmail.com")
                .password("Qq1!pppppp")
                .name("jiwon박")
                .phone("01026961397")
                .alias("aliaspark")
                .build();

        // when
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
            .post("/v1/member")
            .content(gson.toJson(request))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        // then
        assertTrue(contentAsString.contains("\"code\":\"200\""));
    }

}