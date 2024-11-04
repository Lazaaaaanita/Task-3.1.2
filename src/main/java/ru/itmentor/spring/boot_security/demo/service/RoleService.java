package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;


public interface RoleService {
    List<Role> getAllRoles();
}
