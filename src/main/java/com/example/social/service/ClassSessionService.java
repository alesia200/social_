package com.example.social.service;

import com.example.social.model.ClassSession;
import com.example.social.repository.ClassSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassSessionService {

    private final ClassSessionRepository classSessionRepository;

    @Autowired
    public ClassSessionService(ClassSessionRepository classSessionRepository) {
        this.classSessionRepository = classSessionRepository;
    }

    // Получить все занятия (полное расписание)
    public List<ClassSession> findAll() {
        return classSessionRepository.findAll();
    }

    // Найти занятие по ID
    public ClassSession findById(Long id) {
        return classSessionRepository.findById(id).orElse(null);
    }

    // Сохранить/обновить занятие
    public void save(ClassSession classSession) {
        classSessionRepository.save(classSession);
    }

    // Удалить занятие
    public void delete(Long id) {
        classSessionRepository.deleteById(id);
    }

    // Получить занятия конкретного ребенка (Журнал ребенка)
    public List<ClassSession> findByChildId(Long childId) {
        // Этот метод мы сейчас добавим в репозиторий
        return classSessionRepository.findByChildId(childId);
    }

    // Получить занятия конкретного дефектолога (Расписание дефектолога)
    public List<ClassSession> findByDefectologistId(Long defectologistId) {
        // Этот метод мы сейчас добавим в репозиторий
        return classSessionRepository.findByDefectologistId(defectologistId);
    }
}