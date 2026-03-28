package com.example.social.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "icf_assessments")
public class IcfAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_icf_assessment")
    private Long id;

    @NotNull(message = "Укажите протокол диагностики")
    @ManyToOne
    @JoinColumn(name = "ID_protocol")
    private DiagnosticProtocol protocol;

    @NotBlank(message = "Укажите код МКФ")
    @Pattern(regexp = "^[bcdqs][1-9]\\d{0,2}(\\.\\d{1,2})?$",
            message = "Неверный формат кода МКФ. Пример: b110, d450, s760, q150")
    @Column(name = "icf_code", length = 10)
    private String icfCode;

    @NotBlank(message = "Укажите наименование категории")
    @Size(max = 255, message = "Наименование не может превышать 255 символов")
    @Column(name = "category_name", length = 255)
    private String categoryName;

    @NotNull(message = "Укажите квалификатор степени нарушения")
    @Min(value = 0, message = "Минимальное значение квалификатора — 0")
    @Max(value = 4, message = "Максимальное значение квалификатора — 4")
    @Column(name = "qualifier_1")
    private Integer qualifier1;

    @Min(value = 0, message = "Минимальное значение квалификатора — 0")
    @Max(value = 4, message = "Максимальное значение квалификатора — 4")
    @Column(name = "qualifier_2")
    private Integer qualifier2;

    @Size(max = 500, message = "Описание не может превышать 500 символов")
    @Column(name = "description", length = 500)
    private String description;

    // --- Геттеры и сеттеры ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public DiagnosticProtocol getProtocol() { return protocol; }
    public void setProtocol(DiagnosticProtocol protocol) { this.protocol = protocol; }
    public String getIcfCode() { return icfCode; }
    public void setIcfCode(String icfCode) { this.icfCode = icfCode; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public Integer getQualifier1() { return qualifier1; }
    public void setQualifier1(Integer qualifier1) { this.qualifier1 = qualifier1; }
    public Integer getQualifier2() { return qualifier2; }
    public void setQualifier2(Integer qualifier2) { this.qualifier2 = qualifier2; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}