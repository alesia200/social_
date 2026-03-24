package com.example.social.repository;

import com.example.social.model.IcfAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Репозиторий для работы с сущностью IcfAssessment (оценка по МКФ).
 */
public interface IcfAssessmentRepository extends JpaRepository<IcfAssessment, Long> {

    /**
     * Находит все оценки для указанного протокола диагностики.
     * @param protocolId идентификатор протокола
     * @return список оценок
     */
    List<IcfAssessment> findByProtocolId(Long protocolId);
}