package com.example.social.repository;

import com.example.social.model.ClassSession;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClassSessionRepository extends JpaRepository<ClassSession, Long> {

    // Найти все занятия по ID ребенка
    List<ClassSession> findByChildId(Long childId);

    // Найти все занятия по ID дефектолога
    List<ClassSession> findByDefectologistId(Long defectologistId);
}