package com.team1.TBBCloneCoding.member.entity;

import com.team1.TBBCloneCoding.common.entity.TimeStamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Member extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String summary;

    @Builder
    public Member(String loginId, String password, String nickname, String summary) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.summary = summary;
    }

}
