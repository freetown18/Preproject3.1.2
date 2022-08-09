package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> getAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void saveNew(User user) {
        em.persist(user);
    }

    @Override
    public User getById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public void delete(int id) {
        User user = getById(id);
        em.remove(user);
    }

    @Override
    public User getByName(String name) {
        return em.createQuery("select u from User u where u.username = :name", User.class)
                .setParameter("name", name)
                .getSingleResult();
    }



    @Override
    public void update(User user) {
        em.merge(user);
    }
}
