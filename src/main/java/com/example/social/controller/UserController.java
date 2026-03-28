package com.example.social.controller;

import com.example.social.model.User;
import com.example.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
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

    //Показать список всех пользователей
    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/user-list";
    }

    //Показать форму создания пользователя
    @GetMapping("/create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User()); //пустой объект
        return "user/user-form"; // имя HTML файла
    }
    //Показать форму редактирования
    @GetMapping("/update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user/user-form";
    }

    @PostMapping("/save")
    public String saveUser(@Valid @ModelAttribute("user") User user, // Добавляем @Valid и имя модели
                           BindingResult bindingResult, // Добавляем BindingResult
                           Model model) {

        // Проверка ошибок валидации
        if (bindingResult.hasErrors()) {
            // Если ошибки — возвращаем ту же форму
            return "user/user-form";
        }

        userService.save(user);
        return "redirect:/admin/users";
    }

    //Удалить пользователя
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }


}