package com.example.demo.service;

import com.example.demo.model.entity.Author;
import com.example.demo.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private static final String AUTHORS_PATH = "src/main/resources/files/authors.txt";
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors() throws IOException {
        if(authorRepository.count() > 0) {
            return;
        }
        Files.readAllLines(Path.of(AUTHORS_PATH))
                .forEach(fullName -> {
                    String[] split = fullName.split("\\s+");
                    Author author = new Author(split[0],split[1]);
                    authorRepository.save(author);
                });

    }
}
