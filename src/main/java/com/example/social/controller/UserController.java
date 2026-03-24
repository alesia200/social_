package com.example.social.controller;

import com.example.social.model.User;
import com.example.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users") // Все методы этого контроллера будут начинаться с /admin/users
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 1. Показать список всех пользователей
    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user-list"; // имя HTML файла, который мы создадим позже
    }

    // 2. Показать форму создания пользователя
    @GetMapping("/create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User()); // Создаем пустой объект для формы
        return "user-form"; // имя HTML файла
    }

    // 3. Сохранить пользователя (админ создает нового)
    @PostMapping("/save")
    public String saveUser(User user) {
        // ВАЖНО: В реальном приложении здесь нужно шифровать пароль!
        // Пока сохраняем как есть.
        userService.save(user);
        return "redirect:/admin/users"; // Перенаправление обратно к списку
    }

    // 4. Удалить пользователя
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }

    // 5. Показать форму редактирования
    @GetMapping("/update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-form";
    }
}