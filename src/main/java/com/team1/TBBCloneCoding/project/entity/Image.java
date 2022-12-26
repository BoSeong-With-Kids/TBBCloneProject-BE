package com.team1.TBBCloneCoding.project.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "Project_Id", nullable = false)
    private Project project;

    @Builder
    public Image(String imageUrl, Project project) {
        this.imageUrl = imageUrl;
        this.project = project;
    }

    public void imageConnectionWithProject(Project project){
        this.project = project;
    }

}
