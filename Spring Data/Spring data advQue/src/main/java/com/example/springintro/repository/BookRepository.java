package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    //next

    List<Book> findAllByAgeRestrictionEquals(AgeRestriction ageRestriction);

    List<Book> findAllByCopiesLessThan(int copies);

    @Query("SELECT b FROM Book b WHERE b.price NOT BETWEEN :int1 AND :int2")
    List<Book> findAllByPriceNotBetween(BigDecimal int1, BigDecimal int2);

   List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate lower, LocalDate upper);

   List<Book> findAllByTitleContaining(String s);

   List<Book> findAllByAuthor_LastNameStartsWith(String s);

   @Query(value = "SELECT b FROM Book b WHERE length(b.title) > :num")
   List<Book> findAllByTitleGreaterThan(int num);

   List<Book> findAllByAuthor_FirstNameEquals(String name);
}
