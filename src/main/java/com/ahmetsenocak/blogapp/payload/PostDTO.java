package com.ahmetsenocak.blogapp.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Schema(
        description = "PostDTO model information"
)
public class PostDTO {
    private Long id;

    @Schema(description = "Blog Post Title")
    @NotEmpty
    @Size(min = 2, message = "title must be at least 2 character")
    private String title;

    @Schema(description = "Blog Post description")
    @NotEmpty
    @Size(min = 10, message = "Title must be at least 10 character")
    private String description;

    @Schema(description = "Blog Post content")
    @NotEmpty
    private String content;
    private Set<CommentDTO> comments;

    @Schema(description = "Blog Post category")
    private Long categoryId;
}
