package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.RoleService;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String usersPage(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/add")
    public String addPage(ModelMap model){
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "add";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user){
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam(value="id") long id, ModelMap model){
        model.addAttribute("user",userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "edit";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute User user, @RequestParam ("roles") Set<Role> roles){
        user.setRoles(roles);
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete")
    public String deleteCustomerForm(@RequestParam long id) {
        userService.remove(id);
        return "redirect:/admin";
    }

}