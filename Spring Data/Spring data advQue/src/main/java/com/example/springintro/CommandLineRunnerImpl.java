package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.Book;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ex number");
        int exNum = Integer.parseInt(scanner.nextLine());

        switch (exNum) {
            case 1: exOne(scanner);
            case 2: exTwo(scanner);
            case 3: exThree(scanner);
            case 4: exFour(scanner);
            case 5: exFive(scanner);
            case 6: exSix(scanner);
            case 7: exSeven(scanner);
            case 8: exEight(scanner);
            case 9: exNine(scanner);
            case 10: exTen(scanner);

        }
    }

    private void exTen(Scanner scanner) {
        System.out.println("Enter Author firs name:");
        String name = scanner.nextLine();

        List<Book> allCopiesOfAuthor = this.bookService.findAllCopiesOfAuthor(name);

        int counter = 0;

        for (int i = 0; i < allCopiesOfAuthor.size() - 1; i++) {
            Book book = allCopiesOfAuthor.get(i);
            Integer copies = book.getCopies();
            counter+= copies;

        }
        System.out.println(counter);


    }

    private void exNine(Scanner scanner) {
        System.out.println("Enter symbol count");
        int num = Integer.parseInt(scanner.nextLine());

        List<String> booksWithTitleLongerThan = this.bookService.findBooksWithTitleLongerThan(num);
        System.out.println(booksWithTitleLongerThan.size());;
    }

    private void exEight(Scanner scanner) {
        System.out.println("Enter string");
        String s = scanner.nextLine();

        this.bookService.findByAuthorLastNameStartingWith(s).forEach(System.out::println);
    }

    private void exSeven(Scanner scanner) {
        System.out.println("Enter string: ");
        String s = scanner.nextLine();

        this.bookService.findByString(s).forEach(System.out::println);
    }

    private void exSix(Scanner scanner) {
        System.out.println("Enter ending with");
        String ending = scanner.nextLine();

        this.authorService.findByEndingWith(ending).stream().map(Author::toString).forEach(System.out::println);
    }

    private void exFive(Scanner scanner) {
        System.out.println("Enter date");
        String[] split = scanner.nextLine().split("-");
        LocalDate date = LocalDate.of(Integer.parseInt(split[2]), Integer.parseInt(split[1]), Integer.parseInt(split[0]));

        this.bookService.findAllByDateBefore(date).stream().map(Book::toString).forEach(System.out::println);
    }

    private void exFour(Scanner scanner) {
        System.out.println("Enter year");
        int year = Integer.parseInt(scanner.nextLine());
        LocalDate lower = LocalDate.of(year,1,1);
        LocalDate upper = LocalDate.of(year,12,31);
        this.bookService.findAllNotInYear(lower,upper).forEach(System.out::println);

    }

    private void exThree(Scanner scanner) {
        System.out.println("Enter lower bound:");
        BigDecimal int1 = new BigDecimal(scanner.nextLine());
        System.out.println("Enter higher bound:");
        BigDecimal int2 = new BigDecimal(scanner.nextLine());

        this.bookService.findAllNotIn(int1, int2).forEach(System.out::println);
    }

    private void exTwo(Scanner scanner) {
        System.out.println("Please enter amount of copies");
        int copies = Integer.parseInt(scanner.nextLine());
        this.bookService.findAllWithCopiesLessThan(copies).forEach(System.out::println);
    }

    private void exOne(Scanner scanner) {
        System.out.println("Please enter age restriction: ");
        AgeRestriction ageRestriction = AgeRestriction.valueOf(scanner.nextLine().toUpperCase());
        this.bookService.findAllByAgeRestrictionMethod(ageRestriction).forEach(System.out::println);
    }

    private void pritnALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
