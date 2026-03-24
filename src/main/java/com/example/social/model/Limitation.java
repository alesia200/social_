package com.example.social.model;

import jakarta.persistence.*;

@Entity
@Table(name = "limitations")
public class Limitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_limitation")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_disability_info")
    private DisabilityInfo disabilityInfo;

    @Column(name = "category_name", length = 255)
    private String categoryName;

    @Column(name = "degree")
    private Integer degree;

    // --- Геттеры и сеттеры ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public DisabilityInfo getDisabilityInfo() { return disabilityInfo; }
    public void setDisabilityInfo(DisabilityInfo disabilityInfo) { this.disabilityInfo = disabilityInfo; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public Integer getDegree() { return degree; }
    public void setDegree(Integer degree) { this.degree = degree; }
}