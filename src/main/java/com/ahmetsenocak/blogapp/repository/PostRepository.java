package com.ahmetsenocak.blogapp.repository;

import com.ahmetsenocak.blogapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
