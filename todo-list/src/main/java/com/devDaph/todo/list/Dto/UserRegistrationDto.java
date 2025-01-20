package com.devDaph.todo.list.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationDto {
    @NotBlank(message = "Username is required")
    @Size(min = 3, message = "username must have at least 3 characters")
    private String userName;

    @NotBlank(message = "Firstname is required")
    private String firstName;

    @NotBlank(message = "Lastname is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min=6, message = "Password must have at least 6 characters")
    private String password;

    @NotBlank(message = "Confirm Password required")
    private String confirmPassword;

    private Boolean agreeToTerms;

}
