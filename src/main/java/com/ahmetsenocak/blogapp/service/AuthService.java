package com.ahmetsenocak.blogapp.service;

import com.ahmetsenocak.blogapp.payload.LoginDTO;
import com.ahmetsenocak.blogapp.payload.RegisterDTO;

public interface AuthService {
    String login (LoginDTO loginDTO);

    String register(RegisterDTO registerDTO);
}
