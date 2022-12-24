package com.team1.TBBCloneCoding.project.entity;

import com.team1.TBBCloneCoding.common.entity.TimeStamp;
import com.team1.TBBCloneCoding.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @Column(nullable = false)
    private Long goalPrice;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @ElementCollection
    @Column(nullable = true)
    private List<Integer> imageList;

    @ManyToOne
    @JoinColumn(name = "Member_Id", nullable = false)
    private Member member;

    @Builder
    public Project(String category, String title, String summary, Long goalPrice, LocalDateTime startDate, LocalDateTime endDate, Member member, List<Integer> imageList) {
        this.category = category;
        this.title = title;
        this.summary = summary;
        this.goalPrice = goalPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.member = member;
        this.imageList = imageList;
    }
}
