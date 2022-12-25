package com.team1.TBBCloneCoding.project.entity;

import com.team1.TBBCloneCoding.common.entity.TimeStamp;
import com.team1.TBBCloneCoding.member.entity.Member;
import com.team1.TBBCloneCoding.project.dto.ProjectUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
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

    @Column(nullable = false)
    private int recommendCount;

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

    /*
    private String category;
    @Size(min=0,max=30, message = "30자 미만으로 작성해주세요")
    private String title;
    @Size(min=0,max=150, message = "150자 미만으로 작성해주세요")
    private String summary;
    private Long goalPrice;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    */
    public void update(ProjectUpdateRequestDto projectUpdateRequestDto) {
        this.category = projectUpdateRequestDto.getCategory();
        this.title = projectUpdateRequestDto.getTitle();
        this.summary = projectUpdateRequestDto.getSummary();
        this.goalPrice = projectUpdateRequestDto.getGoalPrice();
        this.startDate = projectUpdateRequestDto.getStartDate();
        this.endDate = projectUpdateRequestDto.getEndDate();
    }
}
