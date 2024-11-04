package ru.itmentor.spring.boot_security.demo.dao;

import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao  {
    List<User> getAllUsers();
    void save(User user);
    void remove(Long id);
    void update(User user);
    User getUserById(Long id);
    User getUserByName(String name);

}
