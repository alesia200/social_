package com.example.social.repository;

import com.example.social.model.User;
import org.springframework.data.jpa.repository.JpaRepository; // Эту строку IDEA добавит сама или через Alt+Enter
import java.util.Optional;

// Вот исправленная строка:
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}