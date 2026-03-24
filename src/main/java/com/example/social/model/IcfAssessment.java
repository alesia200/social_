package com.example.social.model;

import jakarta.persistence.*;

@Entity
@Table(name = "icf_assessments")
public class IcfAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Assessment")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_protocol")
    private DiagnosticProtocol protocol;

    @ManyToOne
    @JoinColumn(name = "ID_category")
    private IcfCategory icfCategory;

    @Column(name = "score")
    private Integer score;

    // --- Геттеры и сеттеры ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public DiagnosticProtocol getProtocol() { return protocol; }
    public void setProtocol(DiagnosticProtocol protocol) { this.protocol = protocol; }
    public IcfCategory getIcfCategory() { return icfCategory; }
    public void setIcfCategory(IcfCategory icfCategory) { this.icfCategory = icfCategory; }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
}