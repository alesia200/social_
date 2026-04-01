package com.example.social.repository;

import com.example.social.model.ClassSession;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ClassSessionRepository extends JpaRepository<ClassSession, Long> {
    List<ClassSession> findByChildId(Long childId);
    List<ClassSession> findByDefectologistId(Long defectologistId);
    List<ClassSession> findByChildIdAndSessionDateBetween(Long childId, LocalDate startDate, LocalDate endDate);
}