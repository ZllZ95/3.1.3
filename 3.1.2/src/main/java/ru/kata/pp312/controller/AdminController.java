package ru.kata.pp312.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.pp312.model.User;
import ru.kata.pp312.service.RoleService;
import ru.kata.pp312.service.UserService;


@Controller
@RequestMapping("/admin")
@Transactional
public class AdminController {


    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.index());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "users/index";
    }


    @GetMapping("/show")
    public String show(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.show(id));
        return "users/show";
    }


    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "users/new";
    }


    @PostMapping("/create")
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult, Model model) {
        if (userService.findByUsername(user.getUsername()) != null) {
            bindingResult.rejectValue("username", "error.user", "Пользователь с таким именем уже существует");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("allRoles", roleService.getAllRoles());
            return "users/new";
        }
        userService.save(user);
        return "redirect:/admin";
    }


    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.show(id));
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "users/edit";
    }


    @PostMapping("/update")
    public String update(@RequestParam("id") int id,
                         @ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult, Model model) {
        User existingUser = userService.findByUsername(user.getUsername());
        if (existingUser != null && existingUser.getId() != id) {
            bindingResult.rejectValue("username", "error.user", "Пользователь с таким именем уже существует");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("allRoles", roleService.getAllRoles());
            return "users/edit";
        }
        userService.update(id, user);
        return "redirect:/admin";
    }


    @PostMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
