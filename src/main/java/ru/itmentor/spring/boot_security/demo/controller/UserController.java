package ru.itmentor.spring.boot_security.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.DTO.UserDTO;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.security.Principal;


@RestController
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }
    @GetMapping(value = "/user")
    public ResponseEntity<UserDTO> usersPage(Principal principal) {
        return ResponseEntity.ok(userService.getUserDTOByName(principal.getName()));
    }
}