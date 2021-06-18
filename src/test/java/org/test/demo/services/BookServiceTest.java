package org.test.demo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.test.demo.entities.Author;
import org.test.demo.entities.Book;
import org.test.demo.entities.Name;

import java.util.Collections;

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
        author.setName(new Name("Tomy", "Ivanov", "Green"));
        Book book = new Book();
        book.setAuthor(Collections.singleton(author));
        book.setTitle("Green day");
        book.setPages(320);
        bookService.save(book);
    }

}