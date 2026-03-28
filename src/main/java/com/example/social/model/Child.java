package com.example.social.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "children")
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_child")
    private Long id;
    //Общая информация
    @NotBlank(message = "Фамилия не может быть пустой")
    @Size(max = 100, message = "Фамилия не должна превышать 100 символов")
    @Column(name = "surname", length = 100)
    private String surname;

    @NotBlank(message = "Имя не может быть пустым")
    @Size(max = 100, message = "Имя не должно превышать 100 символов")
    @Column(name = "name", length = 100)
    private String name;

    @Size(max=100, message = "Отчество не должно превышать 100 символов")
    @Column(name = "patronymic", length = 100)
    private String patronymic;

    @NotNull(message = "Дата рождения не может быть пустой")
    @PastOrPresent(message = "Дата рождения не может быть в будущем")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NotNull(message = "Пожалуйста, выберите пол")
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 10)
    private Gender gender;

    @NotBlank(message = "СНИЛС не может быть пустым значением")
    @Size(max=14, message = "СНИЛС не должен превышать 14 символов")
    @Column(name = "snils", length = 14)
    private String snils;

    @Column(name = "actual_address", length = 500)
    @Size(max=500, message = "Адрес не должен превышать 500 символов")
    private String actualAddress;

    @Column(name = "registration_address", length = 500)
    @Size(max=500, message = "Адрес не должен превышать 500 символов")
    private String registrationAddress;

    //Информация о законном представителе
    //Документ, удостоверяющий личность законного (уполномоченного) представителя
    @Column(name = "doc_series", length = 10)
    @Size(max=10, message = "Серия паспорта не должен быть меньше 10 символов")
    private String docSeries;

    @Column(name = "doc_number", length = 20)
    @Size(max=10, message = "Номер паспорта не должен быть меньше 10 символов")
    private String docNumber;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Введите корректный номер телефона")
    @Column(name = "representative_phone", length = 20)
    private String representativePhone;

    @Size(max = 100, message = "ФИО представителя не должно превышать 100 символов")
    @Column(name = "representative_relation", length = 100)
    private String representativeRelation;

    @Size(max = 100, message = "ФИО законного представителя не должно превышать 100 символов")
    @Column(name = "representative_fio", length = 100)
    private String representativeFio;

    // Связи
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_disability_info")
    private DisabilityInfo disabilityInfo;

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
    private List<ClassSession> sessions;

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
    private List<RehabilitationCourse> courses;

    // --- Геттеры и сеттеры ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPatronymic() { return patronymic; }
    public void setPatronymic(String patronymic) { this.patronymic = patronymic; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public String getSnils() { return snils; }
    public void setSnils(String snils) { this.snils = snils; }
    public String getActualAddress() { return actualAddress; }
    public void setActualAddress(String actualAddress) { this.actualAddress = actualAddress; }
    public String getRegistrationAddress() { return registrationAddress; }
    public void setRegistrationAddress(String registrationAddress) { this.registrationAddress = registrationAddress; }
    public String getDocSeries() { return docSeries; }
    public void setDocSeries(String docSeries) { this.docSeries = docSeries; }
    public String getDocNumber() { return docNumber; }
    public void setDocNumber(String docNumber) { this.docNumber = docNumber; }
    public String getRepresentativePhone() { return representativePhone; }
    public void setRepresentativePhone(String representativePhone) { this.representativePhone = representativePhone; }
    public String getRepresentativeRelation() { return representativeRelation; }
    public void setRepresentativeRelation(String representativeRelation) { this.representativeRelation = representativeRelation; }
    public String getRepresentativeFio() { return representativeFio; }
    public void setRepresentativeFio(String representativeFio) { this.representativeFio = representativeFio; }
    public DisabilityInfo getDisabilityInfo() { return disabilityInfo; }
    public void setDisabilityInfo(DisabilityInfo disabilityInfo) { this.disabilityInfo = disabilityInfo; }
    public List<ClassSession> getSessions() { return sessions; }
    public void setSessions(List<ClassSession> sessions) { this.sessions = sessions; }
    public List<RehabilitationCourse> getCourses() { return courses; }
    public void setCourses(List<RehabilitationCourse> courses) { this.courses = courses; }
}