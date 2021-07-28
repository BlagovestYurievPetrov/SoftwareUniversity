package com.example.json.service;

import com.example.json.model.entity.User;
import org.springframework.stereotype.Service;

import java.io.IOException;

public interface UserService {
    void seedUsers() throws IOException;

    User findRandom();
}
