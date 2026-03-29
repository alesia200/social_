package com.example.social.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "disability_info")

public class DisabilityInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_disability_info")
    private Long id;

//    @Pattern(regexp = "^(1|2|3|Ребенок-инвалид)$",
//            message = "Допустимые значения: 1, 2, 3 или Ребенок-инвалид")
    @Column(name = "disability_group", length = 50)
    private String disabilityGroup;

    @Column(name = "is_first_time")
    private Boolean isFirstTime;

    @PastOrPresent(message = "Дата установления не может быть в будущем")
    @Column(name = "establishment_date")
    private LocalDate establishmentDate;

    @FutureOrPresent(message = "Дата окончания не может быть в прошлом")
    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Size(max = 255, message = "Причина не может превышать 255 символов")
    @Column(name = "cause", length = 255)
    private String cause;

    @Size(max = 50, message = "Номер ИПРА не может превышать 50 символов")
    @Column(name = "ipra_number", length = 50)
    private String ipraNumber;

    @FutureOrPresent(message = "Срок действия ИПРА не может быть в прошлом")
    @Column(name = "ipra_expiry_date")
    private LocalDate ipraExpiryDate;

//    @Pattern(regexp = "^(I степень|II степень|III степень|IV степень|Не определена)$",
//            message = "Допустимые значения: I степень, II степень, III степень, IV степень, Не определена")
    @Column(name = "rehab_potential", length = 50)
    private String rehabPotential;

//    @Pattern(regexp = "^(Благоприятный|Относительно благоприятный|Неблагоприятный|Не определен)$",
//            message = "Допустимые значения: Благоприятный, Относительно благоприятный, Неблагоприятный, Не определен")
    @Column(name = "rehab_prognosis", length = 50)
    private String rehabPrognosis;

    @Column(name = "is_palliative")
    private Boolean isPalliative;

    @Valid
    @OneToMany(mappedBy = "disabilityInfo", cascade = CascadeType.ALL)
    private List<Limitation> limitations;

    // --- Геттеры и сеттеры ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDisabilityGroup() { return disabilityGroup; }
    public void setDisabilityGroup(String disabilityGroup) { this.disabilityGroup = disabilityGroup; }
    public Boolean getIsFirstTime() { return isFirstTime; }
    public void setIsFirstTime(Boolean isFirstTime) { this.isFirstTime = isFirstTime; }
    public LocalDate getEstablishmentDate() { return establishmentDate; }
    public void setEstablishmentDate(LocalDate establishmentDate) { this.establishmentDate = establishmentDate; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    public String getCause() { return cause; }
    public void setCause(String cause) { this.cause = cause; }
    public String getIpraNumber() { return ipraNumber; }
    public void setIpraNumber(String ipraNumber) { this.ipraNumber = ipraNumber; }
    public LocalDate getIpraExpiryDate() { return ipraExpiryDate; }
    public void setIpraExpiryDate(LocalDate ipraExpiryDate) { this.ipraExpiryDate = ipraExpiryDate; }
    public String getRehabPotential() { return rehabPotential; }
    public void setRehabPotential(String rehabPotential) { this.rehabPotential = rehabPotential; }
    public String getRehabPrognosis() { return rehabPrognosis; }
    public void setRehabPrognosis(String rehabPrognosis) { this.rehabPrognosis = rehabPrognosis; }
    public Boolean getIsPalliative() { return isPalliative; }
    public void setIsPalliative(Boolean isPalliative) { this.isPalliative = isPalliative; }
    public List<Limitation> getLimitations() { return limitations; }
    public void setLimitations(List<Limitation> limitations) { this.limitations = limitations; }
}