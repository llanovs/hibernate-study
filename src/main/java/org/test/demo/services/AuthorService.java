package org.test.demo.services;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.test.demo.entities.Author;

import java.util.List;

import static org.test.demo.utils.HibernateUtil.getSessionFactory;

public class AuthorService {

    private final Session session = getSessionFactory().openSession();

    public List<Author> getAuthors() {
        return session.createQuery("from Author", Author.class).getResultList();
    }

    public Author getAuthorById(Long id) {
        return session.get(Author.class, id);
    }

    public Author getAuthorByIdQuery(Long id) {
        Query<Author> query = session.createQuery("from Author where id= :id", Author.class)
                .setParameter("id", id);
        query.setCacheable(true);
        return query.getSingleResult();
    }

    public void save(Author author) {
        session.beginTransaction();
        session.save(author);
        session.getTransaction().commit();
    }

    public void delete(Author author) {
        session.beginTransaction();
        session.delete(author);
        session.getTransaction().commit();
    }
}
