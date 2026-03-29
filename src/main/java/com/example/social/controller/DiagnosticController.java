package com.example.social.controller;

import com.example.social.model.DiagnosticProtocol;
import com.example.social.service.DiagnosticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses/{courseId}/diagnostics")
public class DiagnosticController {

    private final DiagnosticService diagnosticService;

    @Autowired
    public DiagnosticController(DiagnosticService diagnosticService) {
        this.diagnosticService = diagnosticService;
    }

    // Открытие формы первичной диагностики
    // Сложная логика (создание 50 пустых строк МКФ) спрятана внутри diagnosticService.getOrCreatePrimaryProtocol()
    @GetMapping("/primary/edit")
    public String editPrimary(@PathVariable Long courseId, Model model) {
        DiagnosticProtocol protocol = diagnosticService.getOrCreateProtocol(courseId, "primary");
        model.addAttribute("protocol", protocol);
        return "fragments/child/diagnostics :: form-view"; // Возвращаем фрагмент вкладки
    }

    // Открытие формы повторной диагностики
    @GetMapping("/secondary/edit")
    public String editSecondary(@PathVariable Long courseId, Model model) {
        DiagnosticProtocol protocol = diagnosticService.getOrCreateProtocol(courseId, "secondary");
        model.addAttribute("protocol", protocol);
        return "fragments/child/diagnostics :: form-view";
    }

    // Сохранение протокола со всеми оценками из таблицы
    @PostMapping("/save")
    public String saveProtocol(@PathVariable Long courseId, DiagnosticProtocol protocol) {
        // Spring сам соберет список assessments из таблицы благодаря th:field
        diagnosticService.save(protocol);

        // Возвращаемся в профиль ребенка (в идеале - на вкладку диагностики, но пока просто в профиль)
        Long childId = diagnosticService.findChildIdByCourseId(courseId);
        return "redirect:/children/" + childId;
    }
}