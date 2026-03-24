package com.example.social.controller;

import com.example.social.model.ClassSession;
import com.example.social.model.User;
import com.example.social.service.ClassSessionService;
import com.example.social.service.ChildService;
import com.example.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sessions") // Базовый URL для занятий
public class ClassSessionController {

    private final ClassSessionService sessionService;
    private final ChildService childService;
    private final UserService userService;

    @Autowired
    public ClassSessionController(ClassSessionService sessionService, ChildService childService, UserService userService) {
        this.sessionService = sessionService;
        this.childService = childService;
        this.userService = userService;
    }

    // 1. Показать полное расписание (для Соц. работника)
    @GetMapping
    public String findAllSessions(Model model) {
        model.addAttribute("sessions", sessionService.findAll());
        return "session-list";
    }

    // 2. Показать форму создания занятия
    // Соц. работник выбирает ребенка и дефектолога
    @GetMapping("/create")
    public String createSessionForm(Model model) {
        model.addAttribute("session", new ClassSession());
        // Передаем списки детей и пользователей для выпадающих списков
        model.addAttribute("children", childService.findAll());
        model.addAttribute("users", userService.findAll());
        return "session-form";
    }

    // 3. Сохранить занятие (создать запись в журнале/расписании)
    @PostMapping("/save")
    public String saveSession(ClassSession session) {
        sessionService.save(session);
        return "redirect:/sessions";
    }

    // 4. Показать журнал конкретного ребенка
    // Доступно: Дефектолог, Соц. работник
    @GetMapping("/child/{childId}")
    public String findSessionsByChild(@PathVariable("childId") Long childId, Model model) {
        model.addAttribute("sessions", sessionService.findByChildId(childId));
        model.addAttribute("child", childService.findById(childId));
        return "child-journal"; // Отдельная страница журнала
    }

    // 5. Показать расписание конкретного дефектолога
    // Доступно: Дефектолог
    @GetMapping("/defectologist/{userId}")
    public String findSessionsByDefectologist(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("sessions", sessionService.findByDefectologistId(userId));
        model.addAttribute("user", userService.findById(userId));
        return "defectologist-schedule";
    }

    // 6. Форма редактирования/заполнения журнала
    // Дефектолог проставляет статус и примечания
    @GetMapping("/update/{id}")
    public String updateSessionForm(@PathVariable("id") Long id, Model model) {
        ClassSession session = sessionService.findById(id);
        model.addAttribute("session", session);
        return "session-update"; // Форма обновления статуса
    }

    // 7. Удалить занятие
    @GetMapping("/delete/{id}")
    public String deleteSession(@PathVariable("id") Long id) {
        sessionService.delete(id);
        return "redirect:/sessions";
    }
}