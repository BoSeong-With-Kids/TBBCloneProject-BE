package com.team1.TBBCloneCoding.comment.entity;

import com.team1.TBBCloneCoding.project.entity.Project;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "Project_Id", nullable = false)
    private Project project;

    @Builder
    public Comment(String contents, Project project) {
        this.contents = contents;
        this.project = project;
    }
}
