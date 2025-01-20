package com.devDaph.todo.list.service;

import com.devDaph.todo.list.Dto.UserRegistrationDto;

public interface UserService {
    void register(UserRegistrationDto userDto);
    boolean existsByEmail(String email);
}
