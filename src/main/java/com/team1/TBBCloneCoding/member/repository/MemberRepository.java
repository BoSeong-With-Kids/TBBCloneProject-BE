package com.team1.TBBCloneCoding.member.repository;

import com.team1.TBBCloneCoding.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {}
