package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.dao.UserDao;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repository.RoleRepository;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserDao userDao;

    private final RoleRepository roleRepository;
    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserDao userDao, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }
    @Transactional
    @Override
    public void remove(Long id) {
        userDao.remove(id);
    }

    @Transactional
    @Override
    public void update(User user) {
        if(user.getPassword().equals(getUserById(user.getId()).getPassword())){
            userDao.update(user);}
        else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.update(user);
        }
    }
    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByName(String name){
        return userDao.getUserByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user= userDao.getUserByName(name);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }
}
