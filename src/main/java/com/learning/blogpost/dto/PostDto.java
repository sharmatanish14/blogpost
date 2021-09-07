package com.learning.blogpost.dto;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;

@Data
public class PostDto {
    private Integer id;
    @NotBlank
    private String content;
    @NotBlank
    private String title;
    private String username;

}
