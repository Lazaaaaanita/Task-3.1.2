package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.itmentor.spring.boot_security.demo.DTO.UserDTO;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();
    void save(User user);
    void remove(Long id);
    void update(User user, Long id);
    User getUserById(Long id);
    User getUserByName(String name);
    List<UserDTO> getAllUsersDTO();
    void saveDTO(UserDTO userDTO);
    void updateDTO(UserDTO userDTO, Long id);
    UserDTO getUserDTOByName(String name);
}
