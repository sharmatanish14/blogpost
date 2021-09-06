package com.learning.blogpost.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterRequest {

    private String username;
    private String password;
    private String email;
}
