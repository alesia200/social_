package com.example.social.service;

import com.example.social.config.CustomUserDetails;
import com.example.social.model.User;
import com.example.social.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
// Имплементируем UserDetailsService для работы Spring Security
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Добавляем шифровальщик

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Метод, который Spring Security использует для поиска пользователя при логине
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Оборачиваем вашу сущность в CustomUserDetails
        return new CustomUserDetails(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // При сохранении шифруем пароль!
    // Исправленный код UserService
    public void save(User user) {
        //Если это редактирование (ID не null)
        if (user.getId() != null) {
            User existingUser = userRepository.findById(user.getId()).orElse(null);

            // Если пароль в форме пустой — оставляем старый из базы
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                user.setPassword(existingUser.getPassword());
            } else {
                // Если пароль ввели новый — шифруем его
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
        } else {
            // Если это создание — просто шифруем пароль
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    // Метод для обновления (чтобы не шифровать пароль каждый раз, если он не менялся)
    public void update(User user) {
        userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}