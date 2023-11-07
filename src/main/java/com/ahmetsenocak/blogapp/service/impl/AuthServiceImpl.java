package com.ahmetsenocak.blogapp.service.impl;

import com.ahmetsenocak.blogapp.entity.Role;
import com.ahmetsenocak.blogapp.entity.User;
import com.ahmetsenocak.blogapp.exception.BlogApiException;
import com.ahmetsenocak.blogapp.payload.LoginDTO;
import com.ahmetsenocak.blogapp.payload.RegisterDTO;
import com.ahmetsenocak.blogapp.repository.RoleRepository;
import com.ahmetsenocak.blogapp.repository.UserRepository;
import com.ahmetsenocak.blogapp.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "User Logged - in Successfully!";
    }

    @Override
    public String register(RegisterDTO registerDTO) {
        // checking fir username exists in db
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Username is already exists!.");
        }

        // checking for email exists in DB
        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "email is already exists!.");
        }

        User user = new User();
        user.setName(registerDTO.getName());
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);
        return "User registered successfully!";
    }
}
