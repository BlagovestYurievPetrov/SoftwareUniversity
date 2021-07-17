package com.example.demo;

import com.example.demo.model.entity.Book;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import com.example.demo.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
        //printAllBooksAfterYear();
        //printAllAuthorsWithBooksBefore(1990);
        //printAllAuthorsByBookCount();
        printAllBooksByAuthorName("George","Powell");
    }

    private void printAllBooksByAuthorName(String firstName, String lastName) {
        bookService.findAllBooksFromAuthor(firstName, lastName)
        .forEach(System.out::println);
    }

    private void printAllAuthorsByBookCount() {
        authorService.getAllAuthorsByBookCount()
        .forEach(System.out::println);

    }


    private void printAllAuthorsWithBooksBefore(int year) {
        List<String> allAuthorsWithBooksBeforeYear = bookService.findAllAuthorsWithBooksBeforeYear(year);
        allAuthorsWithBooksBeforeYear.forEach(System.out::println);
    }

    private void printAllBooksAfterYear() {
        List<Book> allBooksAfterYear = bookService.findAllBooksAfterYear(2000);
        allBooksAfterYear.forEach((book -> {
            System.out.println(book.getTitle());
        }));
    }
}
