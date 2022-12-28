package com.team1.TBBCloneCoding.member.dto;


import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Getter
public class SignupRequestDto {
    @NotBlank
    //@Pattern(regexp="^[a-zA-Z0-9+-\\_.]$")
    private String loginId;
    @NotBlank
    //@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,}")
    private String password;
    @NotBlank
    private String nickname;
}
