package com.ahmetsenocak.blogapp.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDTO {
    private long id;
    @NotEmpty(message = "Name should not be empty!")
    private String name;
    @NotEmpty(message = "Email should not be empty!")
    @Email
    private String email;
    @NotEmpty
    @Size(min = 10, message = "Comment must be at least 10 character")
    private String body;

}
