package com.team1.TBBCloneCoding.member.service;

import com.team1.TBBCloneCoding.member.dto.LoginRequestDto;
import com.team1.TBBCloneCoding.member.dto.SignupRequestDto;
import com.team1.TBBCloneCoding.member.entity.Member;
import com.team1.TBBCloneCoding.member.repository.MemberRepository;
import com.team1.TBBCloneCoding.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {

        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        String nickname = signupRequestDto.getNickname();

        Optional<Member> newPerson = memberRepository.findByUsername(username);

        if(newPerson.isEmpty()) {
            Member member = new Member(username, password, nickname);
            memberRepository.save(member);
        }
    }

    @Transactional
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response){

        String inputUsername = loginRequestDto.getUsername();
        String inputPassword = loginRequestDto.getPassword();

        Member member = memberRepository.findByUsername(inputUsername).orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        if(!passwordEncoder.matches(inputPassword, member.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(member.getUsername()));
    }
}
