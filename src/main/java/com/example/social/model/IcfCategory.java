package com.example.social.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "icf_categories")
public class IcfCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_category")
    private Long id;

    @NotBlank(message = "Код МКФ обязателен")
    @Size(max = 10, message = "Код МКФ не может превышать 10 символов")
    @Column(name = "code_MKF", length = 10)
    private String codeMKF;

    @NotBlank(message = "Наименование категории обязательно")
    @Size(max = 255, message = "Наименование не может превышать 255 символов")
    @Column(name = "name", length = 255)
    private String name;

    // Флаги возрастных групп.
    @Column(name = "is_age_0_3")
    private Boolean isAge03;

    @Column(name = "is_age_4_7")
    private Boolean isAge47;

    @Column(name = "is_age_8_11")
    private Boolean isAge811;

    @Column(name = "is_age_12_17")
    private Boolean isAge1217;

    @OneToMany(mappedBy = "icfCategory")
    private List<IcfAssessment> assessments;

    // --- Геттеры и сеттеры ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodeMKF() { return codeMKF; }
    public void setCodeMKF(String codeMKF) { this.codeMKF = codeMKF; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Boolean getIsAge03() { return isAge03; }
    public void setIsAge03(Boolean isAge03) { this.isAge03 = isAge03; }
    public Boolean getIsAge47() { return isAge47; }
    public void setIsAge47(Boolean isAge47) { this.isAge47 = isAge47; }
    public Boolean getIsAge811() { return isAge811; }
    public void setIsAge811(Boolean isAge811) { this.isAge811 = isAge811; }
    public Boolean getIsAge1217() { return isAge1217; }
    public void setIsAge1217(Boolean isAge1217) { this.isAge1217 = isAge1217; }
    public List<IcfAssessment> getAssessments() { return assessments; }
    public void setAssessments(List<IcfAssessment> assessments) { this.assessments = assessments; }
}