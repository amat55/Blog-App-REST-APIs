package com.ahmetsenocak.blogapp.service;

import com.ahmetsenocak.blogapp.payload.PostDTO;

import java.util.List;


public interface PostService {
    PostDTO createPost(PostDTO postDTO);
    List<PostDTO> getAllPost();
}
