package com.example.social.repository;

import com.example.social.model.ServicePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Репозиторий для работы с сущностью ServicePlan (план услуг).
 */
public interface ServicePlanRepository extends JpaRepository<ServicePlan, Long> {

    /**
     * Находит все планы услуг для указанного реабилитационного курса.
     * @param rehabCourseId идентификатор курса
     * @return список планов услуг
     */
    List<ServicePlan> findByRehabCourseId(Long rehabCourseId);
}