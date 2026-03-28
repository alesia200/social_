package com.example.social.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/defectologist")
public class DefectologistController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}