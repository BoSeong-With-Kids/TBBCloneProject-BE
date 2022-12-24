package com.team1.TBBCloneCoding.project.entity;

import com.team1.TBBCloneCoding.common.entity.TimeStamp;
import com.team1.TBBCloneCoding.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Support extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supportId;

    @Column(nullable = false)
    private Long supportAmount;

    @ManyToOne
    @JoinColumn(name = "Member_Id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "Project_Id", nullable = false)
    private Project project;

    @Builder
    public Support(Long supportAmount, Member member, Project project) {
        this.supportAmount = supportAmount;
        this.member = member;
        this.project = project;
    }
}
