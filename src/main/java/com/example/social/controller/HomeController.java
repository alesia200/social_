package com.example.social.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Authentication authentication, Model model) {
        // Если пользователь не залогинен, то отображение формы авторизации
        if (authentication == null) {
            return "redirect:/login";
        }

        // Можно передать имя пользователя в модель, чтобы поприветствовать его на дашборде
        model.addAttribute("username", authentication.getName());

        // Возвращаем один общий шаблон
        return "dashboard";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}