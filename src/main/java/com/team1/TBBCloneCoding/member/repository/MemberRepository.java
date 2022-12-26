package com.team1.TBBCloneCoding.member.repository;

import com.team1.TBBCloneCoding.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Object> findByLoginId(String userid);

    Optional<Member> findByNickname(String nickname);

    Optional<Object> findByMemberId(Long memberId);
}
