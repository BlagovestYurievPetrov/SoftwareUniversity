package com.example.demo.service;

import com.example.demo.model.entity.Author;
import com.example.demo.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

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

    @Override
    public Author getRandomAuthor() {
        long randomId = ThreadLocalRandom.current().nextLong(1, authorRepository.count() +1);
        return authorRepository.findById(randomId).orElse(null);

    }

    @Override
    public List<String> getAllAuthorsByBookCount() {
        return   authorRepository.findAllByBooksSizeDESC()
                .stream()
                .map(author -> String.format("%s %s %d",author.getFirstName(), author.getLastName(),author.getBooks().size()))
                .collect(Collectors.toList());
    }


}
