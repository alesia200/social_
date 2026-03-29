package com.example.social.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "class_sessions")
public class ClassSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_session")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "ID_service_plan")
    private ServicePlan servicePlan;


    @ManyToOne
    @JoinColumn(name = "ID_child")
    private Child child;


    @ManyToOne
    @JoinColumn(name = "ID_specialist")
    private User defectologist;


    @PastOrPresent(message = "Дата не может быть в будущем")
    @Column(name = "session_date")
    private LocalDate sessionDate;


    @Column(name = "start_time")
    private LocalTime startTime;


    @Pattern(regexp = "^(planned|completed|missed|cancelled)$",
            message = "Допустимые статусы: planned, completed, missed, cancelled")
    @Column(name = "status", length = 20)
    private String status;

    @Size(max = 255, message = "Тема не может превышать 255 символов")
    @Column(name = "topic_theme", length = 255)
    private String topicTheme;

    @Size(max = 500, message = "Цели не могут превышать 500 символов")
    @Column(name = "goals", length = 500)
    private String goals;

    @Size(max = 5000, message = "Заметки не могут превышать 5000 символов")
    @Column(name = "specialist_notes", columnDefinition = "TEXT")
    private String specialistNotes;

    // --- Геттеры и сеттеры ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public ServicePlan getServicePlan() { return servicePlan; }
    public void setServicePlan(ServicePlan servicePlan) { this.servicePlan = servicePlan; }
    public Child getChild() { return child; }
    public void setChild(Child child) { this.child = child; }
    public User getDefectologist() { return defectologist; }
    public void setDefectologist(User defectologist) { this.defectologist = defectologist; }
    public LocalDate getSessionDate() { return sessionDate; }
    public void setSessionDate(LocalDate sessionDate) { this.sessionDate = sessionDate; }
    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getTopicTheme() { return topicTheme; }
    public void setTopicTheme(String topicTheme) { this.topicTheme = topicTheme; }
    public String getGoals() { return goals; }
    public void setGoals(String goals) { this.goals = goals; }
    public String getSpecialistNotes() { return specialistNotes; }
    public void setSpecialistNotes(String specialistNotes) { this.specialistNotes = specialistNotes; }
}