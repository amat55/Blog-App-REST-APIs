package com.ahmetsenocak.blogapp.service;

import com.ahmetsenocak.blogapp.payload.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO createComment(long postId, CommentDTO commentDTO);
    List<CommentDTO> getCommentsByPostId(Long postId);

    CommentDTO getCommentById(Long postId, Long commentId);
}
