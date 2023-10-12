package com.ahmetsenocak.blogapp.service.impl;

import com.ahmetsenocak.blogapp.entity.Post;
import com.ahmetsenocak.blogapp.exception.ResourceNotFoundException;
import com.ahmetsenocak.blogapp.payload.PostDTO;
import com.ahmetsenocak.blogapp.repository.PostRepository;
import com.ahmetsenocak.blogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<PostDTO> getAllPost(int pageNo, int pageSize) {
        // create pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Post> posts = postRepository.findAll(pageable);
        // get content of page object
        List<Post> listOfPost = posts.getContent();
        return listOfPost.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDTO(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, long id) {
        // get post id from DB
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());

        Post updatePost = postRepository.save(post);
        return mapToDTO(updatePost);
    }

    @Override
    public void deleteById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
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
