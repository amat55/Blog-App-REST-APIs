package com.ahmetsenocak.blogapp.service;

import com.ahmetsenocak.blogapp.payload.LoginDTO;

public interface AuthService {
    String login (LoginDTO loginDTO);
}
