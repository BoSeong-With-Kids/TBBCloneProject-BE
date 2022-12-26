package com.team1.TBBCloneCoding.member.service;

import com.team1.TBBCloneCoding.common.exception.ExceptionMessage;
import com.team1.TBBCloneCoding.member.dto.LoginRequestDto;
import com.team1.TBBCloneCoding.member.dto.SignupRequestDto;
import com.team1.TBBCloneCoding.member.entity.Member;
import com.team1.TBBCloneCoding.member.entity.MemberRoleEnum;
import com.team1.TBBCloneCoding.member.repository.MemberRepository;
import com.team1.TBBCloneCoding.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.team1.TBBCloneCoding.common.exception.ExceptionMessage.*;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Value("{admin.token}")
    private String ADMIN_TOKEN;

    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {

        String loginId = signupRequestDto.getLoginId();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        String nickname = signupRequestDto.getNickname();

        //회원 중복확인
        Optional<Object> found = memberRepository.findByLoginId(loginId);
        if(found.isPresent()) {
            throw new IllegalArgumentException(DUPLICATE_USER_ERROR_MSG.getMsg());
        }
        MemberRoleEnum role = MemberRoleEnum.MEMBER;
        if(signupRequestDto.isAdmin()){
            if(!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)){
                throw new IllegalArgumentException(ADMIN_TOKEN_MISMATCH_ERROR_MSG.getMsg());
            }
            role = MemberRoleEnum.ADMIN;
        }

        Member member = new Member(loginId, password, nickname, role);
        memberRepository.save(member);
    }

    @Transactional
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response){

        String inputLoginId = loginRequestDto.getLoginId();
        String inputPassword = loginRequestDto.getPassword();

        Member member = (Member) memberRepository.findByLoginId(inputLoginId).orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.USER_NOT_FOUND_ERROR_MSG.getMsg()));
        if(!passwordEncoder.matches(inputPassword,member.getPassword())){
            throw new IllegalArgumentException(ExceptionMessage.PASSWORDS_DO_NOT_MATCH_ERROR_MSG.getMsg());
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(member.getLoginId(),member.getRole()));
    }

    @Transactional
    public void useridcheck(String loginId) {
        Optional<Object> found = memberRepository.findByLoginId(loginId);
        if(found.isPresent()) {
            throw new IllegalArgumentException(DUPLICATE_USER_ERROR_MSG.getMsg());
        }
    }

    @Transactional
    public void nicknamecheck(String nickname) {
        Optional<Member> found = memberRepository.findByNickname(nickname);
        if(found.isPresent()) {
            throw new IllegalArgumentException(DUPLICATE_NICK_ERROR_MSG.getMsg());
        }
    }
}
