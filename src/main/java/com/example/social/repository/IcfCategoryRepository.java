package com.example.social.repository;

import com.example.social.model.IcfCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с сущностью IcfCategory (справочник МКФ).
 */
public interface IcfCategoryRepository extends JpaRepository<IcfCategory, Long> {
    // Стандартные методы CRUD предоставляются JpaRepository
}