package com.example.social.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "children")
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_child")
    private Long id;

    @Column(name = "surname", length = 100, nullable = false)
    private String surname;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "patronymic", length = 100)
    private String patronymic;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "snils", length = 14)
    private String snils;

    @Column(name = "actual_address", length = 500)
    private String actualAddress;

    @Column(name = "registration_address", length = 500)
    private String registrationAddress;

    @Column(name = "doc_series", length = 10)
    private String docSeries;

    @Column(name = "doc_number", length = 20)
    private String docNumber;

    @Column(name = "representative_phone", length = 20)
    private String representativePhone;

    @Column(name = "representative_relation", length = 20)
    private String representativeRelation;

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
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
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