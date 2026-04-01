package com.example.social.controller;

import com.example.social.model.Child;
import com.example.social.model.ClassSession;
import com.example.social.model.JournalRowDto;
import com.example.social.model.User;
import com.example.social.service.ClassSessionService;
import com.example.social.service.ChildService;
import com.example.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/sessions")
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

    @GetMapping
    public String findAllSessions(Model model) {
        model.addAttribute("sessions", sessionService.findAll());
        return "session-list";
    }

    @GetMapping("/create")
    public String createSessionForm(Model model) {
        model.addAttribute("session", new ClassSession());
        model.addAttribute("children", childService.findAll());
        model.addAttribute("users", userService.findAll());
        return "session-form";
    }

    @PostMapping("/save")
    public String saveSession(ClassSession session) {
        sessionService.save(session);
        return "redirect:/sessions";
    }

    @GetMapping("/child/{childId}")
    public String findSessionsByChild(@PathVariable Long childId,
                                      @RequestParam(required = false) LocalDate startDate,
                                      @RequestParam(required = false) LocalDate endDate,
                                      Model model) {
        Child child = childService.findById(childId);
        List<ClassSession> sessions;
        if (startDate != null && endDate != null) {
            sessions = sessionService.findByChildIdAndDateBetween(childId, startDate, endDate);
        } else {
            sessions = sessionService.findByChildId(childId);
        }

        int total = sessions.size();
        int completed = (int) sessions.stream().filter(s -> "completed".equals(s.getStatus())).count();
        int attendancePercent = total > 0 ? (completed * 100 / total) : 0;

        List<JournalRowDto> rows = sessions.stream()
                .map(JournalRowDto::new)
                .collect(Collectors.toList());

        for (int i = 0; i < rows.size(); i++) {
            rows.get(i).setRowNum(i + 1);
        }

        model.addAttribute("child", child);
        model.addAttribute("childName",
                child.getSurname() + " " + child.getName() +
                        (child.getPatronymic() != null ? " " + child.getPatronymic() : ""));
        model.addAttribute("childBirthYear", child.getBirthDate() != null ? child.getBirthDate().getYear() : "");
        model.addAttribute("childDiagnosis",
                child.getDisabilityInfo() != null ? child.getDisabilityInfo().getCause() : "");
        model.addAttribute("rows", rows);
        model.addAttribute("totalSessions", total);
        model.addAttribute("completedSessions", completed);
        model.addAttribute("attendancePercent", attendancePercent);

        if (!sessions.isEmpty()) {
            LocalDate start = sessions.stream()
                    .map(ClassSession::getSessionDate)
                    .min((d1, d2) -> d1.compareTo(d2))
                    .orElse(LocalDate.now());
            LocalDate end = sessions.stream()
                    .map(ClassSession::getSessionDate)
                    .max((d1, d2) -> d1.compareTo(d2))
                    .orElse(LocalDate.now());
            model.addAttribute("startDate", start);
            model.addAttribute("endDate", end);
        } else {
            model.addAttribute("startDate", LocalDate.now().minusMonths(1));
            model.addAttribute("endDate", LocalDate.now());
        }

        return "children/class-journal";
    }

    @GetMapping("/defectologist/{userId}")
    public String findSessionsByDefectologist(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("sessions", sessionService.findByDefectologistId(userId));
        model.addAttribute("user", userService.findById(userId));
        return "defectologist-schedule";
    }

    @GetMapping("/update/{id}")
    public String updateSessionForm(@PathVariable("id") Long id, Model model) {
        ClassSession session = sessionService.findById(id);
        model.addAttribute("session", session);
        return "session-update";
    }

    @GetMapping("/delete/{id}")
    public String deleteSession(@PathVariable("id") Long id) {
        sessionService.delete(id);
        return "redirect:/sessions";
    }
}