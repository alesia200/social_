package com.example.social.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_user")
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    // Новые поля для ФИО
    @Column(name = "surname", length = 100)
    private String surname;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "patronymic", length = 100)
    private String patronymic;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20, nullable = false)
    private Role role;

    @Column(name = "position", length = 100)
    private String position;

    @OneToMany(mappedBy = "defectologist", cascade = CascadeType.ALL)
    private List<ClassSession> sessions;

    // Конструкторы
    public User() {}

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPatronymic() { return patronymic; }
    public void setPatronymic(String patronymic) { this.patronymic = patronymic; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public List<ClassSession> getSessions() { return sessions; }
    public void setSessions(List<ClassSession> sessions) { this.sessions = sessions; }
}