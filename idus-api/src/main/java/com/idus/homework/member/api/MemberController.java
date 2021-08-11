package com.idus.homework.member.api;

import com.idus.homework.common.ApiResponse;
import com.idus.homework.common.exception.MemberDuplicateException;
import com.idus.homework.common.exception.MemberNotFoundException;
import com.idus.homework.member.app.MemberAppService;
import com.idus.homework.member.app.MemberOrderAppService;
import com.idus.homework.member.representation.MemberDetailResponse;
import com.idus.homework.member.representation.MemberPageResponse;
import com.idus.homework.member.representative.MemberRequest;
import com.idus.homework.member.representative.MemberSearchCondition;
import org.springframework.data.domain.Pageable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("회원 관련")
@RequestMapping("/v1/member")
@RestController
public class MemberController {

    private final MemberAppService memberAppService;
    private final MemberOrderAppService memberOrderAppService;

    public MemberController(MemberAppService memberAppService, MemberOrderAppService memberOrderAppService) {
        this.memberAppService = memberAppService;
        this.memberOrderAppService = memberOrderAppService;
    }

    @ApiOperation(value = "회원 가입 API")
    @PostMapping("")
    public ApiResponse<String> join(@RequestBody @Validated MemberRequest memberRequest) throws MemberDuplicateException {
        memberAppService.join(memberRequest);
        return ApiResponse.ok("회원 가입 성공");
    }

    @ApiOperation(value = "단일 회원 상세 정보 조회 API")
    @GetMapping("/{id}")
    public ApiResponse<MemberDetailResponse> findById(@PathVariable(name = "id") long id) throws MemberNotFoundException {
        return ApiResponse.ok(memberAppService.findById(id));
    }

    @ApiOperation(value = "여러 회원 목록 조회 API")
    @GetMapping("/last-order")
    public ApiResponse<MemberPageResponse> findById(MemberSearchCondition memberSearchCondition,
                                                    @PageableDefault(size = 20) Pageable pageable) {
        return ApiResponse.ok(memberOrderAppService.findAllByCondition(memberSearchCondition, pageable));
    }

}
