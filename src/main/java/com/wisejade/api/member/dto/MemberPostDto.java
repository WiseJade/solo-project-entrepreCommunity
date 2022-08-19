package com.wisejade.api.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@Getter
public class MemberPostDto {
    @NotBlank(message = "이름은 공백이 아니어야 합니다.")
    private String name;

    @NotBlank(message = "비밀번호는 공백이 아니어야 합니다.")
    private String password;

    @Pattern(regexp = "^[FMfm]$")
    private String sex;

    @NotBlank(message = "회사명은 공백이 아니어야 합니다.")
    private String companyName;

    @Pattern(regexp = "^\\d{3}$")
    private String companyType;

    @Pattern(regexp = "^\\d{3}$")
    private String companyLocation;
}
