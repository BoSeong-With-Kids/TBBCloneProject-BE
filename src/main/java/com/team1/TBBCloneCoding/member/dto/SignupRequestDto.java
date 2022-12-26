package com.team1.TBBCloneCoding.member.dto;


import com.team1.TBBCloneCoding.member.entity.MemberRoleEnum;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Getter
public class SignupRequestDto {
    @NotBlank
    @Email
    @Pattern(regexp="^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    private String loginId;
    @NotBlank
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,}")
    private String password; // ( 정리 : 비밀번호에는 공백문자 포함 안되고 최소 영문자 1자, 숫자 1자, 특수문자 1자 포함하고 8자리 이상 )
    // (?=.*[0-9]) => ?=(전방탐색(문법)).*(모든문자 다 탐색) :: 전방에 있는 모든문자를 제외하고( 반환이라는 의미) / [0-9](0부터 9) 1문자([0-9]가 괄호사이에 위치하기 때문)를 반환
    // (?=.*[a-zA-Z]) => ?=.* :: 전방에 있는 모든문자를 제외하고 / [a-zA-Z] 1문자를 반환
    // (?=.*\\W) => ?=.* :: 전방에 있는 모든문자를 제외하고 / \\W(특수문자) 1문자를 반환
    // (?=\\S+$) => ?=\\S :: 공백문자를 제외하고(라는 의미)
    @NotBlank
    private String nickname;
    private boolean isAdmin = false;
    private MemberRoleEnum role;
    private String adminToken = "";
}
