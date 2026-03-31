package com.example.social.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lesson_date")
    private LocalDate date;

    @Column(name = "lesson_time")
    private LocalTime time;

    @Column(name = "lesson_type")
    private String lessonType;

    // Связь с ребенком (ManyToOne - много расписаний к одному ребенку)
    @ManyToOne
    @JoinColumn(name = "child_id")
    private Child child;

    // Связь с дефектологом (ManyToOne)
    @ManyToOne
    @JoinColumn(name = "defectologist_id")
    private User defectologist;

    // Пустой конструктор нужен для Hibernate
    public Schedule() {}

    // Геттеры и Сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }
    public String getLessonType() { return lessonType; }
    public void setLessonType(String lessonType) { this.lessonType = lessonType; }
    public Child getChild() { return child; }
    public void setChild(Child child) { this.child = child; }
    public User getDefectologist() { return defectologist; }
    public void setDefectologist(User defectologist) { this.defectologist = defectologist; }
}