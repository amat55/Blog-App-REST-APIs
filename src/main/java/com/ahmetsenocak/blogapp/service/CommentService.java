package com.ahmetsenocak.blogapp.service;

import com.ahmetsenocak.blogapp.payload.CommentDTO;

public interface CommentService {
    CommentDTO createComment(long postId, CommentDTO commentDTO);
}
