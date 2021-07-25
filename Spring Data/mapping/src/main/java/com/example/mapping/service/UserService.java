package com.example.mapping.service;

import com.example.mapping.model.dto.UserLoginDto;
import com.example.mapping.model.dto.UserRegisterDto;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);

    void loginUser(UserLoginDto userLoginDto);

    void logout();

}
