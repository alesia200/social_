package com.example.social.controller;

import com.example.social.model.Child; // Добавлен импорт ребенка
import com.example.social.model.RehabilitationCourse;
import com.example.social.service.ChildService; // Добавлен импорт сервиса ребенка
import com.example.social.service.RehabilitationCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize; // Добавлен импорт безопасности
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/children/{childId}/courses")
public class CourseController {

    private final RehabilitationCourseService courseService;
    private final ChildService childService; // ДОБАВЛЕН СЕРВИС РЕБЕНКА

    @Autowired
    public CourseController(RehabilitationCourseService courseService, ChildService childService) {
        this.courseService = courseService;
        this.childService = childService; // ИНИЦИАЛИЗАЦИЯ
    }

    // Открытие формы создания нового курса
    @PreAuthorize("hasRole('SOCIAL_WORKER')") // Создавать может только соцработник
    @GetMapping("/new")
    public String createForm(@PathVariable Long childId, Model model) {
        RehabilitationCourse course = courseService.createEmptyCourse(childId);
        model.addAttribute("course", course);
        model.addAttribute("child", childService.findById(childId)); // ИСПРАВЛЕНО: Передаем ребенка
        return "children/course-edit"; // ИСПРАВЛЕНО: Убрана лишняя буква 'c'
    }

    // Сохранение курса (Создание и Редактирование)
    @PreAuthorize("hasRole('SOCIAL_WORKER')") // Сохранять может только соцработник
    // ИСПРАВЛЕНО: Добавлен {courseId} в URL, так как наш HTML-шаблон отправляет данные туда
    @PostMapping("/{courseId}/save")
    public String saveCourse(@PathVariable Long childId, @PathVariable Long courseId, RehabilitationCourse course) {
        // Убеждаемся, что обновляется существующий курс, а не создается новый
        course.setId(courseId);
        courseService.save(course);
        return "redirect:/children/" + childId;
    }

    // Открытие формы редактирования существующего курса (Кнопка "Параметры курса")
    // Доступна Админу, Соцработнику и Дефектологу
    @PreAuthorize("hasAnyRole('ADMIN', 'SOCIAL_WORKER', 'DEFECTOLOGIST')")
    @GetMapping("/{courseId}/edit")
    public String editForm(@PathVariable Long childId,
                           @PathVariable Long courseId,
                           Model model) {
        RehabilitationCourse course = courseService.findById(courseId);
        model.addAttribute("course", course);
        model.addAttribute("child", childService.findById(childId)); // ИСПРАВЛЕНО: Передаем ребенка
        return "children/course-edit"; // ИСПРАВЛЕНО: Направляем на правильный файл
    }

    // Закрытие курса (смена статуса)
    @PreAuthorize("hasRole('SOCIAL_WORKER')")
    @PostMapping("/{courseId}/close")
    public String closeCourse(@PathVariable Long childId,
                              @PathVariable Long courseId) {
        courseService.closeCourse(courseId);
        return "redirect:/children/" + childId;
    }
}