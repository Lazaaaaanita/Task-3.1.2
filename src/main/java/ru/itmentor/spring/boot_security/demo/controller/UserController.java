package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.security.Principal;


@Controller
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService){ this.userService=userService;}

    @GetMapping(value = "/user")
    public String usersPage(ModelMap model, Principal principal) {
        model.addAttribute("users", userService.getUserByName(principal.getName()));
        return "users";
    }
}