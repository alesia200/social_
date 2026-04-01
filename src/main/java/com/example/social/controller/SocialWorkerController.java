package com.example.social.controller;

import com.example.social.model.AttendanceRecordDto;
import com.example.social.model.EffectivenessRecordDto;
import com.example.social.model.AttendanceFilterDto;
import com.example.social.service.ChildService;
import com.example.social.service.ScheduleService;
import com.example.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/social")
public class SocialWorkerController {

    @Autowired
    private ChildService childService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    // Открытие страницы с отчетами
    @GetMapping("/reports")
    public String showReports(Model model) {
        model.addAttribute("filter", new AttendanceFilterDto());
        return "social/reports";
    }

    // AJAX: получение фрагмента с отчетом по посещаемости (GET)
    @GetMapping("/reports/attendance")
    public String getAttendanceFragment(@RequestParam(required = false) Long courseId,
                                        @RequestParam(required = false) Long specialistId,
                                        Model model) {
        // Заглушка – замените на реальные данные из сервиса
        List<AttendanceRecordDto> results = new ArrayList<>();
        results.add(new AttendanceRecordDto("Иванов Иван", 10, 8, 2));
        results.add(new AttendanceRecordDto("Петров Петр", 10, 10, 0));
        results.add(new AttendanceRecordDto("Сидоров Алексей", 10, 5, 5));
        results.add(new AttendanceRecordDto("Смирнова Анна", 10, 9, 1));

        model.addAttribute("results", results);
        return "templates/fragments/child/attendance-result.html";
    }

    // AJAX: получение фрагмента с отчетом по эффективности (GET)
    @GetMapping("/reports/effectiveness")
    public String getEffectivenessFragment(Model model) {
        List<EffectivenessRecordDto> effResults = new ArrayList<>();
        effResults.add(new EffectivenessRecordDto("Иванов Иван", 30.5, 55.0));
        effResults.add(new EffectivenessRecordDto("Петров Петр", 45.0, 48.2));
        effResults.add(new EffectivenessRecordDto("Сидоров Алексей", 20.0, 60.5));
        effResults.add(new EffectivenessRecordDto("Смирнова Анна", 50.0, 52.0));

        model.addAttribute("effResults", effResults);
        return "templates/fragments/child/attendance-result.html";
    }
}