package com.ahmetsenocak.blogapp.service.impl;

import com.ahmetsenocak.blogapp.entity.Post;
import com.ahmetsenocak.blogapp.payload.PostDTO;
import com.ahmetsenocak.blogapp.repository.PostRepository;
import com.ahmetsenocak.blogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        // Converting DTO to entity
        Post post = mapToEntity(new PostDTO());
        Post newPost = postRepository.save(post);

        // Convert entity to DTO
        PostDTO postResponse = mapToDTO(newPost);
        return postResponse;
    }

    @Override
    public List<PostDTO> getAllPost() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
    }

    // convert Entity into DTO
    private PostDTO mapToDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setDescription(post.getDescription());
        postDTO.setContent(post.getContent());
        return postDTO;
    }

    // convert DTO to Entity
    private Post mapToEntity(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());
        return post;
    }
}
