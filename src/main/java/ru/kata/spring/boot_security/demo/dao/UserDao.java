package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();

    void saveNew(User user);

    void update(User user);

    User getById(int id);

    void delete(int id);

    User getByName(String name);
}
