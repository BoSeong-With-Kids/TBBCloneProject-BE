package com.team1.TBBCloneCoding.member.controller;

import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.member.dto.LoginRequestDto;
import com.team1.TBBCloneCoding.member.dto.SignupRequestDto;
import com.team1.TBBCloneCoding.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/tumblebug/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        memberService.signup(signupRequestDto);
        return new ResponseEntity(new ResponseDto("success", "회원가입이 완료되었습니다.", null), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        memberService.login(loginRequestDto, response);
        return new ResponseEntity(new ResponseDto("success", "로그인에 성공했습니다.", null), HttpStatus.OK);
    }
}