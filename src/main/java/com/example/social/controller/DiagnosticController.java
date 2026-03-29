package com.example.social.controller;

import com.example.social.model.DiagnosticProtocol;
import com.example.social.service.DiagnosticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/courses/{courseId}/diagnostics")
public class DiagnosticController {

    private final DiagnosticService diagnosticService;

    @Autowired
    public DiagnosticController(DiagnosticService diagnosticService) {
        this.diagnosticService = diagnosticService;
    }

    // Открытие формы первичной диагностики
    @GetMapping("/primary/edit")
    public String editPrimary(@PathVariable Long courseId, Model model) {
        DiagnosticProtocol protocol = diagnosticService.getOrCreateProtocol(courseId, "primary");
        model.addAttribute("protocol", protocol);
        // ИЗМЕНЕНО: Возвращаем полноценную страницу бланка, а не фрагмент вкладки
        return "diagnostics/diagnostics-form";
    }

    // Открытие формы повторной диагностики
    @GetMapping("/secondary/edit")
    public String editSecondary(@PathVariable Long courseId, Model model) {
        DiagnosticProtocol protocol = diagnosticService.getOrCreateProtocol(courseId, "secondary");
        model.addAttribute("protocol", protocol);
        return "diagnostics/diagnostics-form";
    }

    // Сохранение протокола со всеми оценками из таблицы
    @PostMapping("/save")
    public String saveProtocol(@PathVariable Long courseId,
                               DiagnosticProtocol protocol,
                               RedirectAttributes redirectAttributes) {

        // Spring сам соберет список assessments из таблицы благодаря th:field
        diagnosticService.save(protocol);

        // ИЗМЕНЕНО: Добавляем сообщение об успехе
        redirectAttributes.addFlashAttribute("successMessage", "Протокол успешно сохранен!");

        // ИЗМЕНЕНО: Делаем редирект НА ЭТУ ЖЕ СТРАНИЦУ ПРОТОКОЛА (в ту же новую вкладку).
        // protocol.getDiagnosticType() вернет "primary" или "secondary",
        // поэтому пользователь останется на открытой вкладке с протоколом.
        return "redirect:/courses/" + courseId + "/diagnostics/" + protocol.getDiagnosticType() + "/edit";
    }
}