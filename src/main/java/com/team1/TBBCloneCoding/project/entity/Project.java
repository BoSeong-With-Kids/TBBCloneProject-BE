package com.team1.TBBCloneCoding.project.entity;

import com.team1.TBBCloneCoding.common.entity.TimeStamp;
import com.team1.TBBCloneCoding.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Project extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String summary;

    @Column(nullable = true)
    private String content;

    @Column(nullable = false)
    private Long goalPrice;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;


    @ManyToOne
    @JoinColumn(name = "Member_Id", nullable = false)
    private Member member;

    @Builder
    public Project(String category, String title, String summary, String content, Long goalPrice, LocalDateTime startDate, LocalDateTime endDate, Member member) {
        this.category = category;
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.goalPrice = goalPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.member = member;
    }
}
