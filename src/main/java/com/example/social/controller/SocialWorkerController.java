package com.example.social.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/social")
public class SocialWorkerController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "social/dashboard";
    }
}