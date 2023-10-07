package com.ahmetsenocak.blogapp.service;

import com.ahmetsenocak.blogapp.payload.PostDTO;
import org.springframework.stereotype.Service;


public interface PostService {
    PostDTO createPost(PostDTO postDTO);
}
