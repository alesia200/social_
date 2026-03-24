package com.example.social.repository;

import com.example.social.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepository extends JpaRepository<Child, Long> {
    // JpaRepository уже содержит методы:
    // save() - сохранить/обновить
    // findAll() - найти всех
    // findById() - найти по ID
    // deleteById() - удалить по ID
}