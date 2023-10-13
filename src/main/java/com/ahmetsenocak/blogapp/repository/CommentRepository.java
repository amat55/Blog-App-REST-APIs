package com.ahmetsenocak.blogapp.repository;

import com.ahmetsenocak.blogapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
