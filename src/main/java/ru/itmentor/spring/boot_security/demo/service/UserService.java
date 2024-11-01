package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();
    void save(User user);
    void remove(Long id);
    void update(User user);
    User getUserById(Long id);
    User getUserByName(String name);
}
