package com.example.social.controller;

import com.example.social.model.RehabilitationCourse;
import com.example.social.service.RehabilitationCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/children/{childId}/courses")
public class CourseController {

    private final RehabilitationCourseService courseService;

    @Autowired
    public CourseController(RehabilitationCourseService courseService) {
        this.courseService = courseService;
    }

    // Открытие формы создания нового курса
    @GetMapping("/new")
    public String createForm(@PathVariable Long childId, Model model) {
        // Контроллер не проверяет, можно ли создать курс. Это сделает Service!
        RehabilitationCourse course = courseService.createEmptyCourse(childId);
        model.addAttribute("course", course);
        model.addAttribute("childId", childId);
        return "courses/course-form"; // Отдельная небольшая форма курса
    }

    // Сохранение курса
    @PostMapping("/save")
    public String saveCourse(@PathVariable Long childId, RehabilitationCourse course) {
        courseService.save(course);
        // После сохранения курса возвращаемся в профиль ребенка
        return "redirect:/children/" + childId;
    }

    // Открытие формы редактирования существующего курса
    @GetMapping("/{courseId}/edit")
    public String editForm(@PathVariable Long childId,
                           @PathVariable Long courseId,
                           Model model) {
        RehabilitationCourse course = courseService.findById(courseId);
        model.addAttribute("course", course);
        model.addAttribute("childId", childId);
        return "courses/course-form";
    }

    // Закрытие курса (смена статуса)
    @PostMapping("/{courseId}/close")
    public String closeCourse(@PathVariable Long childId,
                              @PathVariable Long courseId) {
        courseService.closeCourse(courseId);
        return "redirect:/children/" + childId;
    }
}