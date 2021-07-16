package com.example.demo.service;

import com.example.demo.model.entity.Author;

import java.io.IOException;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();
}
