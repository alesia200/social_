package com.example.social.controller;

import com.example.social.model.Child;
import com.example.social.model.DisabilityInfo;
import com.example.social.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/children")
public class ChildController {

    private final ChildService childService;

    @Autowired
    public ChildController(ChildService childService) {
        this.childService = childService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("children", childService.findAll());
        return "child-list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        Child child = childService.findById(id);
        model.addAttribute("child", child);
        return "child-profile"; // Убедитесь, что файл child-profile.html существует
    }

    @GetMapping("/create")
    public String createChildForm(Model model) {
        Child child = new Child();
        // Инициализируем пустой объект, чтобы форма работала корректно
        child.setDisabilityInfo(new DisabilityInfo());
        model.addAttribute("child", child);
        return "child-form";
    }

    // ИСПРАВЛЕННЫЙ МЕТОД СОХРАНЕНИЯ
    // Мы убрали MultipartFile и логику сохранения файлов, так как в новом ТЗ нет поля для пути к файлу
    @PostMapping("/save")
    public String saveChild(Child child) {

        // Если у ребенка еще нет инфо об инвалидности (на всякий случай), создаем объект
        if (child.getDisabilityInfo() == null) {
            child.setDisabilityInfo(new DisabilityInfo());
        }

        childService.save(child);
        return "redirect:/children";
    }

    @GetMapping("/update/{id}")
    public String updateChildForm(@PathVariable("id") Long id, Model model) {
        Child child = childService.findById(id);
        model.addAttribute("child", child);
        return "child-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteChild(@PathVariable("id") Long id) {
        childService.delete(id);
        return "redirect:/children";
    }
}