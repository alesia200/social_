package com.example.social.service;

import com.example.social.model.Child;
import com.example.social.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildService {

    private final ChildRepository childRepository;

    @Autowired
    public ChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    // Получить список всех детей (для Соц. работника и Дефектолога)
    public List<Child> findAll() {
        return childRepository.findAll();
    }

    // Найти ребенка по ID (для просмотра профиля)
    public Child findById(Long id) {
        return childRepository.findById(id).orElse(null);
    }

    // Сохранить или обновить профиль ребенка
    // Благодаря каскадированию (CascadeType.ALL) в модели Child,
    // при сохранении ребенка сохранятся и связанные DisabilityInfo
    public void save(Child child) {
        childRepository.save(child);
    }

    // Удалить профиль ребенка
    public void delete(Long id) {
        childRepository.deleteById(id);
    }
}