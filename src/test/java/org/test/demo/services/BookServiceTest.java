package org.test.demo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.test.demo.entities.Author;
import org.test.demo.entities.Book;
import org.test.demo.entities.Name;

import java.util.Collections;
import java.util.List;

class BookServiceTest {

    private static BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookService();
    }

    @Test
    void shouldReturnAllBooks() {
        System.out.println(bookService.getBooks());
    }

    @Test
    void shouldReturnBookById() {
        Book book = bookService.getBookById(2L);
        System.out.println(book);
        System.out.println(book.getAuthor());
    }

    @Test
    void shouldReturnSaveAuthor() {
        Author author = new Author();
        author.setName(new Name("Ivan", "Pivo", "Sivash"));
        Book book = new Book();
        book.setAuthor(Collections.singleton(author));
        book.setTitle("Green day2");
        book.setPages(220);
        bookService.save(book);
    }

    @Test
    void shouldReturnListBookWhenLessThan250Pages() {
        bookService.getBookWithPagesLessThan250(250).forEach(System.out::println);
    }

    @Test
    void shouldReturnBookData() {
        List<Object[]> objects = bookService.getBooksData();
        objects.forEach(obj -> System.out.println(new StringBuilder().append("title: ")
                .append(obj[0]).append(", pages: ")
                .append(obj[1]).toString()));
    }
}