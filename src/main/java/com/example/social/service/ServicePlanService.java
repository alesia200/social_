package com.example.social.service;

import com.example.social.model.ServicePlan;
import com.example.social.repository.ServicePlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для работы с планами услуг (ServicePlan).
 */
@Service
public class ServicePlanService {

    private final ServicePlanRepository servicePlanRepository;

    @Autowired
    public ServicePlanService(ServicePlanRepository servicePlanRepository) {
        this.servicePlanRepository = servicePlanRepository;
    }

    /**
     * Получить все планы услуг.
     * @return список всех планов
     */
    public List<ServicePlan> findAll() {
        return servicePlanRepository.findAll();
    }

    /**
     * Найти план услуг по идентификатору.
     * @param id идентификатор плана
     * @return план услуг или null
     */
    public ServicePlan findById(Long id) {
        return servicePlanRepository.findById(id).orElse(null);
    }

    /**
     * Найти все планы услуг для указанного реабилитационного курса.
     * @param rehabCourseId идентификатор курса
     * @return список планов
     */
    public List<ServicePlan> findByRehabCourseId(Long rehabCourseId) {
        return servicePlanRepository.findByRehabCourseId(rehabCourseId);
    }

    /**
     * Сохранить или обновить план услуг.
     * @param servicePlan план для сохранения
     */
    public void save(ServicePlan servicePlan) {
        servicePlanRepository.save(servicePlan);
    }

    /**
     * Удалить план услуг по идентификатору.
     * @param id идентификатор плана
     */
    public void delete(Long id) {
        servicePlanRepository.deleteById(id);
    }
}