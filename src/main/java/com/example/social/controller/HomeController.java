package com.example.social.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Authentication authentication) {
        // Если пользователь не залогинен, то отображение формы авторизации
        if (authentication == null) {
            return "redirect:/login";
        }

        // Возвращаем один общий шаблон
        return "dashboard";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}