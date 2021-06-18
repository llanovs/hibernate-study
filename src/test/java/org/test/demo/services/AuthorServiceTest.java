package org.test.demo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.test.demo.entities.Author;
import org.test.demo.entities.Name;

class AuthorServiceTest {

    private static AuthorService authorService;

    @BeforeEach
    void setUp() {
        authorService = new AuthorService();
    }

    @Test
    void shouldReturnAllAuthors() {
        System.out.println(authorService.getAuthors());
    }

    @Test
    void shouldReturnAuthorByIdCashLevel1ForOneSession() {
        //1 level cache provided by Hibernate
        //if the data present in cache 1 level Hibernate gets it from cache

        //session 1
        authorService.getAuthorById(2L);
        authorService.getAuthorById(2L);
    }

    @Test
    void shouldReturnAuthorByIdCashLevel1ForTwoSessions() {
        //Each session has it's own 1 level cache that provided by Hibernate

        //session 1
        authorService.getAuthorById(2L);
        authorService.getAuthorById(2L);

        //session 2
        AuthorService service = new AuthorService();
        service.getAuthorById(2L);
        service.getAuthorById(2L);
        service.getAuthorById(2L);
    }


    @Test
    void shouldReturnAuthorByIdCashLevel2ForTwoSessions() {
        //Each session has the same 2 level cache that not provided by Hibernate
        //should be added, configured and added settings
        //Cache 2 level:
        // EhCache, etc

        //session 1
        authorService.getAuthorById(2L);
        authorService.getAuthorById(2L);

        //session 2
        AuthorService service = new AuthorService();
        service.getAuthorById(2L);
        service.getAuthorById(2L);
        service.getAuthorById(2L);
    }

    @Test
    void shouldReturnAuthorByIdCashLevel2ForTwoSessionsQuery() {

        //session 1
        authorService.getAuthorByIdQuery(2L);
        authorService.getAuthorByIdQuery(2L);

        //session 2
        AuthorService service = new AuthorService();
        service.getAuthorByIdQuery(2L);
        service.getAuthorByIdQuery(2L);
        service.getAuthorByIdQuery(2L);
    }

    @Test
    void shouldReturnAuthorById() {
        System.out.println(authorService.getAuthorById(22L));
    }

    @Test
    void shouldReturnSaveAuthor() {
        Author author = new Author();
        author.setName(new Name("Tomy", "Ivanov", "Green"));
        authorService.save(author);
    }

    @Disabled
    @Test
    void shouldReturnDeleteAuthor() {
        authorService.delete(authorService.getAuthorById(23L));
    }
}