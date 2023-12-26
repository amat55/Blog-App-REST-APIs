package com.ahmetsenocak.blogapp.controller;

import com.ahmetsenocak.blogapp.payload.PostDTO;
import com.ahmetsenocak.blogapp.payload.PostResponse;
import com.ahmetsenocak.blogapp.service.PostService;
import com.ahmetsenocak.blogapp.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/posts")
@Tag(
        name = "CRUD REST APIs for Post Resource"
)
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(
            summary = "Create Post Rest API",
            description = "Create Post Rest API is used to save post into db"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http status 201 Created"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    // Create Blog Post
    @PreAuthorize("hasRole('ADMIN')")   // only admin access to creatPost
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO) {
        return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }


    @Operation(
            summary = "Get all Post Rest API",
            description = "Get all Post Rest API is used to fetch all the posts from the db"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 success"
    )
    // get all rest-api
    @GetMapping()
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir

    ) {
        return postService.getAllPost(pageNo, pageSize, sortBy, sortDir);
    }

    @Operation(
            summary = "Get Post By ID Rest API",
            description = "Get Post By ID Rest API is used to get single post from the db"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 success"
    )
    // get post by id
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @Operation(
            summary = "Update Post Rest API",
            description = "Update Post Rest API is used to update single post in the db"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 success"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    // update post by id
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO,
                                              @PathVariable(name = "id") long id) {

        PostDTO postResponse = postService.updatePost(postDTO, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete Post Rest API",
            description = "Delete Post Rest API is used to Delete single post in the db"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 success"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    // delete post by id
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") long id) {
        postService.deleteById(id);
        return new ResponseEntity<>("Successfully deleted!", HttpStatus.OK);
    }

    // build get post by category Rest Api
    // http://localhost:8080/api/posts/category/3
    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable("id") Long categoryId) {
        List<PostDTO> postDTOS = postService.getPostByCategory(categoryId);
        return ResponseEntity.ok(postDTOS);
    }
}
