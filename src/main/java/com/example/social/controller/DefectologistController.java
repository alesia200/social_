package com.example.social.controller;

import com.example.social.model.Schedule;
import com.example.social.model.User;
import com.example.social.service.ScheduleService;
import com.example.social.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/defectologist")
public class DefectologistController {

    private final ScheduleService scheduleService;
    private final UserService userService;

    public DefectologistController(ScheduleService scheduleService, UserService userService) {
        this.scheduleService = scheduleService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    // Страница личного расписания дефектолога
    @GetMapping("/schedule")
    public String showMySchedule(Model model) {

        // 1. Получаем логин (username) текущего пользователя
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // 2. ИСПРАВЛЕНО: Ищем по username, а не по email
        User currentDefectologist = userService.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        // 3. Получаем ВЕСЬ список расписаний из БД
        List<Schedule> allSchedules = scheduleService.findAllSorted();

        // 4. Оставляем ТОЛЬКО те занятия, где дефектолог - это он сам
        List<Schedule> mySchedules = allSchedules.stream()
                .filter(s -> s.getDefectologist().getId().equals(currentDefectologist.getId()))
                .collect(Collectors.toList());

        model.addAttribute("schedules", mySchedules);
        return "defectologist/schedule";
    }
}