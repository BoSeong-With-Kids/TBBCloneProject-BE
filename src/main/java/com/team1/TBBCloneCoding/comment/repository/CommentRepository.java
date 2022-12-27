package com.team1.TBBCloneCoding.comment.repository;

import com.team1.TBBCloneCoding.comment.entity.Comment;
import com.team1.TBBCloneCoding.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByOrderByLastModifiedAtDesc();

}
