package com.example.social.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_user")
    private Long id;

    @NotBlank(message = "Логин не может быть пустым")
    @Size(min = 3, max = 100, message = "Логин должен быть от 3 до 100 символов")
    @Column(name = "username", length = 100, nullable = false, unique = true)
    private String username;

    @Size(min = 5, max = 255, message = "Пароль должен быть не менее 5 символов")
    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @NotBlank(message = "Фамилия не может быть пустой")
    @Size(max = 100, message = "Фамилия не должна превышать 100 символов")
    @Column(name = "surname", length = 100)
    private String surname;

    @NotBlank(message = "Имя не может быть пустым")
    @Size(max = 100, message = "Имя не должно превышать 100 символов")
    @Column(name = "name", length = 100)
    private String name;

    @Size(max = 100, message = "Отчество не должно превышать 100 символов")
    @Column(name = "patronymic", length = 100)
    private String patronymic;

    // === Валидация Роли ===
    @NotNull(message = "Необходимо выбрать роль")
    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20, nullable = false)
    private Role role;

    @Size(max = 100, message = "Должность не должна превышать 100 символов")
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