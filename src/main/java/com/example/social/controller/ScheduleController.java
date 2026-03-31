package com.example.social.controller;

import com.example.social.model.*;
import com.example.social.service.ChildService;
import com.example.social.service.ScheduleService;
import com.example.social.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ChildService childService;
    private final UserService userService;

    public ScheduleController(ScheduleService scheduleService,
                              ChildService childService,
                              UserService userService) {
        this.scheduleService = scheduleService;
        this.childService = childService;
        this.userService = userService;
    }

    // Общее расписание (без привязки к ребёнку)
    @GetMapping
    public String showGeneralSchedule(Model model) {
        List<Schedule> schedules = scheduleService.findAllSorted();
        List<User> defectologists = userService.findAllByRole(Role.DEFECTOLOGIST);
        List<Child> children = childService.findAll();

        model.addAttribute("schedules", schedules);
        model.addAttribute("defectologists", defectologists);
        model.addAttribute("children", children);
        model.addAttribute("scheduleForm", new ScheduleFormDto());

        return "schedule-form/general";
    }

    // Расписание для конкретного ребёнка
    @GetMapping("/child/{childId}")
    public String showChildSchedule(@PathVariable Long childId, Model model) {
        Child child = childService.findById(childId);
        List<Schedule> schedules = scheduleService.findByChildId(childId);
        List<User> defectologists = userService.findAllByRole(Role.DEFECTOLOGIST);

        model.addAttribute("child", child);
        model.addAttribute("schedules", schedules);
        model.addAttribute("defectologists", defectologists);
        model.addAttribute("scheduleForm", new ScheduleFormDto());

        return "schedule-form/child-schedule";
    }

    // Сохранение занятия (общее)
    @PostMapping("/save")
    public String saveSchedule(@ModelAttribute ScheduleFormDto form,
                               @RequestParam(required = false) Long childId) {
        scheduleService.save(form);
        if (childId != null) {
            return "redirect:/schedule/child/" + childId;
        }
        return "redirect:/schedule"; // должно быть /schedule, а не /schedule-form
    }

    // Форма редактирования (можно использовать ту же модалку с параметром)

    @GetMapping("/edit/{id}")
    public String editSchedule(@PathVariable Long id,
                               @RequestParam(required = false) Long childId,
                               Model model) {
        ScheduleFormDto form = scheduleService.getScheduleFormDto(id);
        if (childId != null) {
            // Редактирование из расписания ребёнка
            Child child = childService.findById(childId);
            List<Schedule> schedules = scheduleService.findByChildId(childId);
            List<User> defectologists = userService.findAllByRole(Role.DEFECTOLOGIST);
            model.addAttribute("child", child);
            model.addAttribute("schedules", schedules);
            model.addAttribute("defectologists", defectologists);
            model.addAttribute("scheduleForm", form);
            return "schedule-form/child-schedule";
        } else {
            // Редактирование из общего расписания
            List<Schedule> schedules = scheduleService.findAllSorted();
            List<User> defectologists = userService.findAllByRole(Role.DEFECTOLOGIST);
            List<Child> children = childService.findAll();
            model.addAttribute("schedules", schedules);
            model.addAttribute("defectologists", defectologists);
            model.addAttribute("children", children);
            model.addAttribute("scheduleForm", form);
            return "schedule-form/general";
        }
    }
}
