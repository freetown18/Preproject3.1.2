package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/admin";
    }

    @GetMapping("/user-create")
    public String showNewUserForm(Model model) {
        User user = new User();
        List<Role> roleList = userService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("list", roleList);
        return "admin/user-create";
    }

    @PostMapping("/user-create")
    public String addNewUser(User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/user-update")
    public String ShowUpdateUserForm(@PathVariable("id") int id, Model model) {
        User user = userService.findById(id);
        List<Role> listRoles = userService.getAllRoles();
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("user", user);
        return "admin/user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/user-delete")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}

