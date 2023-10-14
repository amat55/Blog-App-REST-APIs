package com.ahmetsenocak.blogapp.repository;

import com.ahmetsenocak.blogapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(Long postId);

}
