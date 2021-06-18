package org.test.demo.services;

import org.hibernate.Session;
import org.test.demo.dto.AuthorBook;
import org.test.demo.entities.Book;

import java.util.List;

import static org.test.demo.utils.HibernateUtil.getSessionFactory;

public class BookService {

    private static final Session session = getSessionFactory().openSession();

    public List<Book> getBooks(){
        return session.createQuery("from Book", Book.class).getResultList();
    }

    public List<Object[]> getBooksData(){
        return session.createQuery("select title, pages from Book").getResultList();
    }

    public Book getBookById(Long id){
        return session.get(Book.class, id);
    }

    public void save(Book book) {
        session.beginTransaction();
        book.getAuthor().forEach(session::save);
        session.save(book);
        session.getTransaction().commit();
    }

    public List<Book> getBookWithPagesLessThan250(final int limit){
        return session.createQuery("from Book where pages < :limit")
                                        .setParameter("limit", limit)
                                        .getResultList();
    }

    public List<AuthorBook> getAuthorsByBookTitle(){
        return session.createQuery("select title, surname, Name, pages " +
                "from book as b join author as a on a.id = b.author_id;").getResultList();
    }

    public List<AuthorBook> getAuthorsBuBookTitle(){
        return session.createSQLQuery("select b.title, a.surname, a.name, b.pages " +
                "from book as b join author as a on a.id = b.author_id;").getResultList();
    }
}
