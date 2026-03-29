package com.example.social.repository;

import com.example.social.model.DiagnosticProtocol;
import com.example.social.model.RehabilitationCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiagnosticProtocolRepository extends JpaRepository<DiagnosticProtocol, Long> {

    // Ищем протокол конкретного типа у конкретного курса
    Optional<DiagnosticProtocol> findByRehabCourseAndDiagnosticType(RehabilitationCourse rehabCourse, String diagnosticType);
}


