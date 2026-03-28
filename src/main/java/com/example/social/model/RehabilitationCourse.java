package com.example.social.model;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "rehabilitation_courses")

public class RehabilitationCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_rehab_course")
    private Long id;

    @NotNull(message = "Укажите ребёнка")
    @ManyToOne
    @JoinColumn(name = "ID_child")
    private Child child;

    @NotNull(message = "Укажите дату начала курса")
    @PastOrPresent(message = "Дата начала не может быть в будущем")
    @Column(name = "start_date")
    private LocalDate startDate;

    @NotNull(message = "Укажите дату окончания курса")
    @FutureOrPresent(message = "Дата окончания не может быть в прошлом")
    @Column(name = "end_date")
    private LocalDate endDate;

    @NotBlank(message = "Укажите номер договора/направления")
    @Pattern(regexp = "^[\\dА-Яа-яA-Za-z\\-/№ ]+$",
            message = "Номер содержит недопустимые символы")
    @Size(max = 50, message = "Номер не может превышать 50 символов")
    @Column(name = "contract_number", length = 50)
    private String contractNumber;

    @NotBlank(message = "Укажите статус курса")
    @Pattern(regexp = "^(planned|active|completed|cancelled)$",
            message = "Допустимые статусы: planned, active, completed, cancelled")
    @Column(name = "status", length = 20)
    private String status;

    // Может быть null, так как заполняется по итогам курса
    @DecimalMin(value = "0.00", message = "Процент не может быть отрицательным")
    @DecimalMax(value = "100.00", message = "Процент не может превышать 100")
    @Digits(integer = 3, fraction = 2, message = "Формат: до 3 цифр и 2 знаков после запятой")
    @Column(name = "effectiveness_percentage", precision = 5, scale = 2)
    private BigDecimal effectivenessPercentage;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_protocol")
    private DiagnosticProtocol diagnosticProtocol;

    @OneToMany(mappedBy = "rehabCourse", cascade = CascadeType.ALL)
    private List<ServicePlan> servicePlans;

    // --- Геттеры и сеттеры ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Child getChild() { return child; }
    public void setChild(Child child) { this.child = child; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public String getContractNumber() { return contractNumber; }
    public void setContractNumber(String contractNumber) { this.contractNumber = contractNumber; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public BigDecimal getEffectivenessPercentage() { return effectivenessPercentage; }
    public void setEffectivenessPercentage(BigDecimal effectivenessPercentage) { this.effectivenessPercentage = effectivenessPercentage; }
    public DiagnosticProtocol getDiagnosticProtocol() { return diagnosticProtocol; }
    public void setDiagnosticProtocol(DiagnosticProtocol diagnosticProtocol) { this.diagnosticProtocol = diagnosticProtocol; }
    public List<ServicePlan> getServicePlans() { return servicePlans; }
    public void setServicePlans(List<ServicePlan> servicePlans) { this.servicePlans = servicePlans; }
}