package com.idus.homework.member.representative;

import com.idus.homework.member.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequest {
    @Email
    @Pattern(regexp = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$",
        message = "이메일 형식으로 입력해야합니다")
    @Size(max = 100)
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력값입니다")
    @Size(min = 10, max = 20)
    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{10,20}",
        message = "비밀번호는 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함 해야합니다")
    private String password;

    @NotBlank(message = "이름은 필수 입력값입니다")
    @Pattern(regexp = "^[ㄱ-ㅎ|가-힣|a-z|A-Z|]+$", message = "이름은 한글, 영문 대소문자만 허용합니다")
    @Size(max = 20)
    private String name;

    @NotBlank(message = "전화번호는 필수 입력값입니다")
    @Size(max = 20)
    @Pattern(regexp = "^[0-9]*$", message = "전화번호는 숫자만 입력하세요")
    private String phone;

    @NotBlank(message = "별칭은 필수 입력값입니다")
    @Pattern(regexp = "^[a-z]*$", message = "영어 소문자만 입력하세요")
    @Size(max = 30)
    private String alias;

    private Gender gender;
}
