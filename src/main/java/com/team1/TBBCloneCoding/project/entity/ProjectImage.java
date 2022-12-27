package com.team1.TBBCloneCoding.project.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class ProjectImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = true)
    private String whichContent;

    @ManyToOne
    @JoinColumn(name = "Project_Id", nullable = false)
    private Project project;

    @Builder
    public ProjectImage(String imageUrl, Project project) {
        this.imageUrl = imageUrl;
        this.project = project;
    }

    public void contentImageConnectionWithProject(Project project){
        this.project = project;
        this.whichContent = "contentImage";
    }

    public void thumbnailImageConnectionWithProject(Project project){
        this.project = project;
        this.whichContent = "thumbnailImage";
    }


}
