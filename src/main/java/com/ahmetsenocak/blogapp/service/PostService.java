package com.ahmetsenocak.blogapp.service;

import com.ahmetsenocak.blogapp.entity.Post;
import com.ahmetsenocak.blogapp.payload.PostDTO;

import java.util.List;


public interface PostService {
    PostDTO createPost(PostDTO postDTO);

    List<PostDTO> getAllPost(int pageNo, int pageSize);

    PostDTO getPostById(Long id);

    PostDTO updatePost(PostDTO postDTO, long id);

    void deleteById(long id);
}
