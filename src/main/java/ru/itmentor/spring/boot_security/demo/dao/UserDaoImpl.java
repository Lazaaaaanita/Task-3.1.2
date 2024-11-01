package ru.itmentor.spring.boot_security.demo.dao;

import javax.persistence.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void save(User user) {
        entityManager.persist(user);
    }
    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User",User.class).getResultList();
    }

    @Override
    public void remove(Long id) {
        User user1= getUserById(id);
        if(user1!=null)
    entityManager.remove(user1);
    }

    @Override
    public void update(User user) {
    entityManager.merge(user);
    }

    @Override
    public User getUserById(Long id) {
      return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByName(String name) {
        try {
            return  entityManager.createQuery("FROM User u WHERE u.name = :username", User.class)
                    .setParameter("username", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        }
    }

