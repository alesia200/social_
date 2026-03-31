package com.example.social.repository;

import com.example.social.model.Schedule;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // Находит все занятия для конкретного ребёнка
    List<Schedule> findByChildId(Long childId);

    // Находит все занятия для конкретного ребёнка с возможностью сортировки
    List<Schedule> findByChildId(Long childId, Sort sort);

    // Находит все занятия для конкретного дефектолога (если понадобится)
    List<Schedule> findByDefectologistId(Long defectologistId, Sort sort);

    // Находит занятия для ребёнка в заданном временном интервале (для проверки пересечений)
    List<Schedule> findByChildIdAndDateBetween(Long childId, LocalDate startDate, LocalDate endDate);
}