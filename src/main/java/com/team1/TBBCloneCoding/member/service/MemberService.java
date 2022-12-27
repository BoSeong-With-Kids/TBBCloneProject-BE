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

        String loginId = signupRequestDto.getLoginId();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        String nickname = signupRequestDto.getNickname();

        // 로그인 ID 중복 확인
        Optional<Member> loginIdDuplicateCheck = memberRepository.findByLoginId(loginId);
        if(loginIdDuplicateCheck.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디 입니다.");
        }

        // 닉네임 중복 확인
        Optional<Member> nicknameDuplicateCheck = memberRepository.findByNickname(nickname);
        if (nicknameDuplicateCheck.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 닉네임 입니다.");
        }

        Member member = new Member(loginId, password, nickname);
        memberRepository.save(member);
    }

    @Transactional
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response){

        String inputLoginId = loginRequestDto.getLoginId();
        String inputPassword = loginRequestDto.getPassword();

        Member member = memberRepository.findByLoginId(inputLoginId).orElseThrow(
                () -> new IllegalArgumentException("회원을 찾을 수 없습니다.")
        );

        if(!passwordEncoder.matches(inputPassword, member.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(member.getLoginId()));
    }
}
