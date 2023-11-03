package com.ahmetsenocak.blogapp.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDTO {
    private Long id;

    @NotEmpty
    @Size(min = 2, message = "title must be at least 2 character")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Title must be at least 10 character")
    private String description;

    @NotEmpty
    private String content;
    private Set<CommentDTO> comments;
}
