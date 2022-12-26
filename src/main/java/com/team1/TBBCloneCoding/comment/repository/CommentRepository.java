package com.team1.TBBCloneCoding.comment.repository;

import com.team1.TBBCloneCoding.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Long> {
}
