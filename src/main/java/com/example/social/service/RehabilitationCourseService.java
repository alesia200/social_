package com.example.social.service;

import com.example.social.model.Child;
import com.example.social.model.RehabilitationCourse;
import com.example.social.repository.ChildRepository;
import com.example.social.repository.RehabilitationCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RehabilitationCourseService {

    private final RehabilitationCourseRepository courseRepository;
    private final ChildRepository childRepository;

    @Autowired
    public RehabilitationCourseService(RehabilitationCourseRepository courseRepository,
                                       ChildRepository childRepository) {
        this.courseRepository = courseRepository;
        this.childRepository = childRepository;
    }

    /**
     * Бизнес-правило: можно ли создать новый курс?
     */
    public boolean canCreateNewCourse(Child child) {
        if (child.getCourses() == null || child.getCourses().isEmpty()) {
            return true;
        }
        // Находим последний по дате начала курс
        RehabilitationCourse lastCourse = child.getCourses().stream()
                .max(Comparator.comparing(RehabilitationCourse::getStartDate))
                .orElse(null);

        // Новый можно создать только если старый завершен или отменен
        return lastCourse != null &&
                ("completed".equals(lastCourse.getStatus()) || "cancelled".equals(lastCourse.getStatus()));
    }

    /**
     * Создает "заготовку" курса для формы
     */
    @Transactional
    public RehabilitationCourse createEmptyCourse(Long childId) {
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new RuntimeException("Ребенок не найден"));

        if (!canCreateNewCourse(child)) {
            throw new IllegalStateException("Нельзя создать новый курс: текущий курс еще не завершен.");
        }

        RehabilitationCourse course = new RehabilitationCourse();
        course.setChild(child);
        course.setStatus("planned");
        course.setStartDate(LocalDate.now()); // Значение по умолчанию

        // Сохраняем, чтобы получить ID для формы
        return courseRepository.save(course);
    }

    public RehabilitationCourse findById(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Курс реабилитации не найден"));
    }

    public void save(RehabilitationCourse course) {
        courseRepository.save(course);
    }

    /**
     * Бизнес-логика закрытия курса
     */
    @Transactional
    public void closeCourse(Long courseId) {
        RehabilitationCourse course = findById(courseId);
        course.setStatus("completed");
        course.setEndDate(LocalDate.now()); // Автоматически ставим дату закрытия
        courseRepository.save(course);
    }

    // --- Вспомогательные методы для View (чтобы не писать логику в контроллере) ---

    public List<RehabilitationCourse> getCoursesSortedByDate(Child child) {
        if (child.getCourses() == null) return List.of();
        return child.getCourses().stream()
                .sorted(Comparator.comparing(RehabilitationCourse::getStartDate))
                .collect(Collectors.toList());
    }

    public RehabilitationCourse getActiveCourse(Child child) {
        if (child.getCourses() == null) return null;
        return child.getCourses().stream()
                .filter(c -> "active".equals(c.getStatus()))
                .findFirst().orElse(null);
    }
}