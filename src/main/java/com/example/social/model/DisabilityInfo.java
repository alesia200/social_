package com.example.social.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "disability_info")
public class DisabilityInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_disability_info")
    private Long id;

    @Column(name = "disability_group", length = 50)
    private String disabilityGroup;

    @Column(name = "is_first_time")
    private Boolean isFirstTime;

    @Column(name = "establishment_date")
    private LocalDate establishmentDate;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "cause", length = 255)
    private String cause;

    @Column(name = "ipra_number", length = 50)
    private String ipraNumber;

    @Column(name = "ipra_expiry_date")
    private LocalDate ipraExpiryDate;

    @Column(name = "rehab_potential", length = 50)
    private String rehabPotential;

    @Column(name = "rehab_prognosis", length = 50)
    private String rehabPrognosis;

    @Column(name = "is_palliative")
    private Boolean isPalliative;

    // Связь с ограничениями
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