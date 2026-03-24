package com.example.social.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "diagnostic_protocols")
public class DiagnosticProtocol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_protocol")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_rehab_course")
    private RehabilitationCourse rehabCourse;

    @Column(name = "diagnostic_type", length = 20)
    private String diagnosticType;

    @Column(name = "assessment_date")
    private LocalDate assessmentDate;

    @Column(name = "total_score")
    private Integer totalScore;

    @Column(name = "effectiveness_percentage")
    private Integer effectivenessPercentage;

    @OneToMany(mappedBy = "protocol", cascade = CascadeType.ALL)
    private List<IcfAssessment> assessments;

    // --- Геттеры и сеттеры ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public RehabilitationCourse getRehabCourse() { return rehabCourse; }
    public void setRehabCourse(RehabilitationCourse rehabCourse) { this.rehabCourse = rehabCourse; }
    public String getDiagnosticType() { return diagnosticType; }
    public void setDiagnosticType(String diagnosticType) { this.diagnosticType = diagnosticType; }
    public LocalDate getAssessmentDate() { return assessmentDate; }
    public void setAssessmentDate(LocalDate assessmentDate) { this.assessmentDate = assessmentDate; }
    public Integer getTotalScore() { return totalScore; }
    public void setTotalScore(Integer totalScore) { this.totalScore = totalScore; }
    public Integer getEffectivenessPercentage() { return effectivenessPercentage; }
    public void setEffectivenessPercentage(Integer effectivenessPercentage) { this.effectivenessPercentage = effectivenessPercentage; }
    public List<IcfAssessment> getAssessments() { return assessments; }
    public void setAssessments(List<IcfAssessment> assessments) { this.assessments = assessments; }
}