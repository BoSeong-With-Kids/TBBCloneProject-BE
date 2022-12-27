package com.team1.TBBCloneCoding.security;


import com.team1.TBBCloneCoding.member.entity.Member;
import com.team1.TBBCloneCoding.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Member member = memberRepository.findByLoginId(loginId).orElseThrow(
                () -> new IllegalArgumentException("사용자를 찾을 수 없습니다.")
        );
        return new UserDetailsImpl(member, member.getLoginId());
    }
}
