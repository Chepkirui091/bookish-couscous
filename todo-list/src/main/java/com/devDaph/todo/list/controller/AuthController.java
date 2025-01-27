package com.devDaph.todo.list.controller;


import com.devDaph.todo.list.Dto.LoginDto;
import com.devDaph.todo.list.Dto.UserRegistrationDto;
import com.devDaph.todo.list.model.Role;
import com.devDaph.todo.list.model.User;
import com.devDaph.todo.list.repo.RoleRepo;
import com.devDaph.todo.list.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser (@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User Signed-in successfully!.", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?>registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {

        // add check for username exists in a DB
        if(userRepository.existsByUsername(userRegistrationDto.getUserName())){
            return new ResponseEntity<>("Username is already exists!", HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB
        if(userRepository.existsByEmail(userRegistrationDto.getEmail())){
            return new ResponseEntity<>("Email is already exists!", HttpStatus.BAD_REQUEST);
        }

        // create user object
        User user = new User();
        user.setName(userRegistrationDto.getUserName());
        user.setEmail(userRegistrationDto.getEmail());
        user.setUsername(userRegistrationDto.getUserName());
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));

        Role roles = roleRepo.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);

    }

}
