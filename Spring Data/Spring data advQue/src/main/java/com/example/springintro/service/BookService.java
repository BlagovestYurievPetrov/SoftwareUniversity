package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> findAllByAgeRestrictionMethod(AgeRestriction ageRestriction);

    List<String> findAllWithCopiesLessThan(int copies);

    List<String> findAllNotIn(BigDecimal int1, BigDecimal int2);

    List<String> findAllNotInYear(LocalDate lower, LocalDate upper);

    List<Book> findAllByDateBefore(LocalDate date);

    List<String> findByString(String s);

    List<String> findByAuthorLastNameStartingWith(String s);

    List<String> findBooksWithTitleLongerThan(int num);

    List<Book> findAllCopiesOfAuthor(String name);
}
