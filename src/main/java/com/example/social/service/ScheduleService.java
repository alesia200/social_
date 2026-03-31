package com.example.social.service;

import com.example.social.model.Schedule;
import com.example.social.model.ScheduleFormDto;
import com.example.social.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ChildService childService; // сервис для работы с детьми

    @Autowired
    private UserService userService;   // сервис для работы с пользователями (дефектологами)

    /**
     * Сохраняет (создаёт или обновляет) расписание на основе DTO.
     */
    public void save(ScheduleFormDto dto) {
        Schedule schedule;
        if (dto.getId() != null && dto.getId() > 0) {
            schedule = findById(dto.getId());
        } else {
            schedule = new Schedule();
        }

        schedule.setDate(LocalDate.parse(dto.getDate()));
        schedule.setTime(LocalTime.parse(dto.getTime()));
        schedule.setLessonType(dto.getLessonType());
        schedule.setChild(childService.findById(dto.getChildId()));

        // Если defectologistId передан и не 0 (или не null), то устанавливаем
        if (dto.getDefectologistId() != null && dto.getDefectologistId() > 0) {
            schedule.setDefectologist(userService.findById(dto.getDefectologistId()));
        } else {
            schedule.setDefectologist(null);
        }

        scheduleRepository.save(schedule);
    }

    /**
     * Возвращает все занятия, отсортированные по дате и времени.
     */
    public List<Schedule> findAllSorted() {
        return scheduleRepository.findAll(Sort.by(Sort.Direction.ASC, "date", "time"));
    }

    /**
     * Поиск занятия по ID.
     */
    public Schedule findById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Расписание не найдено"));
    }

    /**
     * Возвращает список занятий для конкретного ребёнка.
     */
    public List<Schedule> findByChildId(Long childId) {
        return scheduleRepository.findByChildId(childId);
    }

    /**
     * Преобразует сущность Schedule в ScheduleFormDto для предзаполнения формы редактирования.
     */
    public ScheduleFormDto getScheduleFormDto(Long id) {
        Schedule schedule = findById(id);
        ScheduleFormDto dto = new ScheduleFormDto();
        dto.setId(schedule.getId());
        dto.setDate(schedule.getDate().toString());
        dto.setTime(schedule.getTime().toString());
        dto.setLessonType(schedule.getLessonType());
        dto.setChildId(schedule.getChild().getId());
        dto.setDefectologistId(schedule.getDefectologist() != null ? schedule.getDefectologist().getId() : null);
        return dto;
    }
}