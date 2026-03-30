package com.example.social.controller;

import com.example.social.model.AttendanceFilterDto;
import com.example.social.model.AttendanceRecordDto;
import com.example.social.model.EffectivenessRecordDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/social")
public class SocialWorkerController {

    // ИЗМЕНЕНО: Добавлен Model для передачи расписания
    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        // ==========================================
        // ВРЕМЕННАЯ ЗАГЛУШКА ДЛЯ РАСПИСАНИЯ
        // ==========================================
        // Позже здесь вы вызовете ваш сервис:
        // model.addAttribute("schedules", scheduleService.findAllSchedules());

        List<String> schedules = new ArrayList<>();
        schedules.add("Тестовая запись: расписание пока загружается...");
        model.addAttribute("schedules", schedules);
        // ==========================================

        return "dashboard";
    }

    // Открытие страницы с отчетами
    @GetMapping("/reports")
    public String showReports(Model model) {
        model.addAttribute("filter", new AttendanceFilterDto());
        return "social/reports";
    }

    // ОБРАБОТКА КНОПКИ "СФОРМИРОВАТЬ" (Посещаемость)
    @PostMapping("/reports/attendance")
    public String generateAttendanceReport(@ModelAttribute("filter") AttendanceFilterDto filter, Model model) {

        model.addAttribute("filter", filter);

        List<AttendanceRecordDto> results = new ArrayList<>();
        results.add(new AttendanceRecordDto("Иванов Иван", 10, 8, 2));
        results.add(new AttendanceRecordDto("Петров Петр", 10, 10, 0));
        results.add(new AttendanceRecordDto("Сидоров Алексей", 10, 5, 5));
        results.add(new AttendanceRecordDto("Смирнова Анна", 10, 9, 1));

        model.addAttribute("results", results);
        return "social/reports";
    }

    // ОБРАБОТКА КНОПКИ "СФОРМИРОВАТЬ" (Эффективность по МКФ)
    @PostMapping("/reports/effectiveness")
    public String generateEffectivenessReport(Model model) {

        if (!model.containsAttribute("filter")) {
            model.addAttribute("filter", new AttendanceFilterDto());
        }

        List<EffectivenessRecordDto> effResults = new ArrayList<>();
        effResults.add(new EffectivenessRecordDto("Иванов Иван", 30.5, 55.0));
        effResults.add(new EffectivenessRecordDto("Петров Петр", 45.0, 48.2));
        effResults.add(new EffectivenessRecordDto("Сидоров Алексей", 20.0, 60.5));
        effResults.add(new EffectivenessRecordDto("Смирнова Анна", 50.0, 52.0));

        model.addAttribute("effResults", effResults);
        return "social/reports";
    }
}