package com.ahmetsenocak.blogapp.service;

import com.ahmetsenocak.blogapp.payload.PostDTO;
import com.ahmetsenocak.blogapp.payload.PostResponse;


public interface PostService {
    PostDTO createPost(PostDTO postDTO);

    PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDTO getPostById(Long id);

    PostDTO updatePost(PostDTO postDTO, long id);

    void deleteById(long id);
}
