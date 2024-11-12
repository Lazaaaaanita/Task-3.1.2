package ru.itmentor.spring.boot_security.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import ru.itmentor.spring.boot_security.demo.DTO.UserDTO;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @PersistenceContext
    private EntityManager entityManager;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService=roleService;
        this.modelMapper=modelMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Transactional
    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    @Transactional
    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }
    @Transactional
    @Override
    public void update(User user, Long id) {
        user.setId(id);
        if (user.getPassword().equals(getUserById(user.getId()).getPassword())) {
            userRepository.save(user);
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    }

    @Transactional
    @Override
    public User getUserById(Long id) {
        return userRepository.getById(id);
    }
    @Transactional
    @Override
    public User getUserByName(String name) {
        return userRepository.getUserByName(name);
    }

    @Transactional
    @Override
    public List<UserDTO> getAllUsersDTO() {
        return getAllUsers().stream().map(this::convertToUserDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void saveDTO(UserDTO userDTO) {
        save(convertToUser(userDTO));
    }
    @Transactional
    @Override
    public void updateDTO(UserDTO userDTO, Long id) {
        update(convertToUser(userDTO), id);
    }
    @Transactional
    @Override
    public UserDTO getUserDTOByName(String name) {
        return convertToUserDTO(getUserByName(name));
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user=userRepository.getUserByName(name);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }
    private User convertToUser(UserDTO userDTO) {
        Set<Role> roles=new HashSet<>();
        userDTO.getRoles().forEach(role->roles.add(roleService.getRoleByRoleName(role)));
        User user = modelMapper.map(userDTO,User.class);
        user.setRoles(roles);
        return user;
    }

    private UserDTO convertToUserDTO(User user) {
        modelMapper.map(user, UserDTO.class);
        return modelMapper.map(user, UserDTO.class);
    }
}
