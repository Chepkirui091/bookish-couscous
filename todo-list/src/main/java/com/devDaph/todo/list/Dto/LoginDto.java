package com.devDaph.todo.list.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {
    @NotBlank(message = "Email or Username is required")
    private String usernameOrEmail;

    @NotBlank(message = "Password is required")
    private String password;
}
