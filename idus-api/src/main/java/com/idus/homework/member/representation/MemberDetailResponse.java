package com.idus.homework.member.representation;

import com.idus.homework.member.domain.Gender;
import com.idus.homework.member.domain.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDetailResponse {
    @ApiModelProperty("회원ID")
    private Long id;
    @ApiModelProperty("이메일")
    private String email;
    @ApiModelProperty("이름")
    private String name;
    @ApiModelProperty("전화번호")
    private String phone;
    @ApiModelProperty("별칭")
    private String alias;
    @ApiModelProperty("성별")
    private Gender gender;

    @Builder
    public MemberDetailResponse(Long id, String email, String name, String phone, String alias, Gender gender) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.alias = alias;
        this.gender = gender;
    }

    public static MemberDetailResponse from(Member member) {
        return MemberDetailResponse.builder()
            .id(member.getId())
            .email(member.getEmail())
            .name(member.getName())
            .phone(member.getPhone())
            .alias(member.getAlias())
            .gender(member.getGender())
            .build();
    }
}
