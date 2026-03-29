package com.example.social.service;

import com.example.social.model.DiagnosticProtocol;
import com.example.social.model.IcfAssessment;
import com.example.social.model.IcfCategory;
import com.example.social.model.RehabilitationCourse;
import com.example.social.repository.DiagnosticProtocolRepository;
import com.example.social.repository.IcfCategoryRepository;
import com.example.social.repository.RehabilitationCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiagnosticService {

    private final DiagnosticProtocolRepository protocolRepository;
    private final RehabilitationCourseRepository courseRepository;
    private final IcfCategoryRepository icfCategoryRepository;

    @Autowired
    public DiagnosticService(DiagnosticProtocolRepository protocolRepository,
                             RehabilitationCourseRepository courseRepository,
                             IcfCategoryRepository icfCategoryRepository) {
        this.protocolRepository = protocolRepository;
        this.courseRepository = courseRepository;
        this.icfCategoryRepository = icfCategoryRepository;
    }

    @Transactional
    public DiagnosticProtocol getOrCreateProtocol(Long courseId, String diagnosticType) {
        RehabilitationCourse course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Курс не найден"));

        DiagnosticProtocol protocol = protocolRepository
                .findByRehabCourseAndDiagnosticType(course, diagnosticType)
                .orElse(null);

        if (protocol == null) {
            // 1. Создаем переменную (она больше НИГДЕ не будет переназначаться через =)
            DiagnosticProtocol newProtocol = new DiagnosticProtocol();
            newProtocol.setRehabCourse(course);
            newProtocol.setDiagnosticType(diagnosticType);

            List<IcfCategory> allCategories = icfCategoryRepository.findAll();

            // 2. Лямбда довольна: newProtocol финальная
            List<IcfAssessment> assessments = allCategories.stream().map(category -> {
                IcfAssessment assessment = new IcfAssessment();
                assessment.setIcfCategory(category);
                assessment.setScore(null);
                assessment.setProtocol(newProtocol);
                return assessment;
            }).collect(Collectors.toList());

            newProtocol.setAssessments(assessments);

            // 3. Сохраняем БЕЗ знака равно.
            // Hibernate сам положит ID внутрь newProtocol
            protocolRepository.save(newProtocol);

            course.getDiagnosticProtocols().add(newProtocol);
            courseRepository.save(course);

            // 4. Присваиваем результат переменной protocol для возврата
            protocol = newProtocol;
        }

        return protocol;
    }

    @Transactional
    public void save(DiagnosticProtocol protocol) {
        protocolRepository.save(protocol);
    }

    public Long findChildIdByCourseId(Long courseId) {
        return courseRepository.findById(courseId)
                .map(c -> c.getChild().getId())
                .orElseThrow(() -> new RuntimeException("Курс не найден"));
    }
}