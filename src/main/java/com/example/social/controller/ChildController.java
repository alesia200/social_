package com.example.social.controller;

import com.example.social.model.Child;
import com.example.social.model.DisabilityInfo;
import com.example.social.service.ChildService;
import com.example.social.service.RehabilitationCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/children")
public class ChildController {

    private final ChildService childService;
    private final RehabilitationCourseService courseService;

    @Autowired
    public ChildController(ChildService childService, RehabilitationCourseService courseService) {
        this.childService = childService;
        this.courseService = courseService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("children", childService.findAll());
        return "children/child-list";
    }
    //форма просмотра профиля ребенка
    @GetMapping("/{id}")
    @Transactional
    public String showChildForm(@PathVariable Long id, Model model) {
        Child child = childService.findById(id);
        model.addAttribute("child", child);
        model.addAttribute("activeCourse", courseService.getActiveCourse(child));
        return "children/child-profile";
    }
    //форма редактирования профиля ребенка
    @GetMapping("/edit/{id}")
    public String updateChildForm(@PathVariable Long id, Model model) {
        Child child = childService.findById(id);
        model.addAttribute("child", child);
        return "children/child-form";
    }
    //форма создания нового профиля ребенка
    @GetMapping("/create")
    public String createChildForm(Model model) {
        Child child = new Child();
        // Инициализируем пустой объект, чтобы форма работала корректно
        model.addAttribute("child", child);
        return "children/child-form";
    }
    @PostMapping("/save")
    public String saveChild(Child child) {

        childService.save(child);
        return "redirect:/children";
    }

    @GetMapping("/delete/{id}")
    public String deleteChild(@PathVariable("id") Long id) {
        childService.delete(id);
        return "redirect:/children";
    }
}