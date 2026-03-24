package com.example.social.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "service_plans")
public class ServicePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_service_plan")
    private Long id;

    @Column(name = "service_direction", length = 100)
    private String serviceDirection;

    @ManyToOne
    @JoinColumn(name = "ID_rehab_course")
    private RehabilitationCourse rehabCourse;

    @Column(name = "planned_quantity")
    private Integer plannedQuantity;

    @Column(name = "executed_quantity")
    private Integer executedQuantity;

    @OneToMany(mappedBy = "servicePlan")
    private List<ClassSession> sessions;

    // --- Геттеры и сеттеры ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getServiceDirection() { return serviceDirection; }
    public void setServiceDirection(String serviceDirection) { this.serviceDirection = serviceDirection; }
    public RehabilitationCourse getRehabCourse() { return rehabCourse; }
    public void setRehabCourse(RehabilitationCourse rehabCourse) { this.rehabCourse = rehabCourse; }
    public Integer getPlannedQuantity() { return plannedQuantity; }
    public void setPlannedQuantity(Integer plannedQuantity) { this.plannedQuantity = plannedQuantity; }
    public Integer getExecutedQuantity() { return executedQuantity; }
    public void setExecutedQuantity(Integer executedQuantity) { this.executedQuantity = executedQuantity; }
    public List<ClassSession> getSessions() { return sessions; }
    public void setSessions(List<ClassSession> sessions) { this.sessions = sessions; }
}