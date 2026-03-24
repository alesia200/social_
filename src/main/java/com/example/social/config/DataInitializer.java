package com.example.social.config;

import com.example.social.model.Role;
import com.example.social.model.User;
import com.example.social.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Проверяем, есть ли уже пользователи
        if (userRepository.count() == 0) {
            // Создаём администратора
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode(" ")); // пароль admin
            admin.setRole(Role.ADMIN);
            admin.setSurname("Администратор");
            admin.setName("Системы");
            admin.setPatronymic(""); // или можно оставить null
            admin.setPosition("Администратор");
            userRepository.save(admin);

            // (Опционально) Создаём тестового социального работника
            User socialWorker = new User();
            socialWorker.setUsername("social");
            socialWorker.setPassword(passwordEncoder.encode(" "));
            socialWorker.setRole(Role.SOCIAL_WORKER);
            socialWorker.setSurname("Петрова");
            socialWorker.setName("Анна");
            socialWorker.setPatronymic("Ивановна");
            socialWorker.setPosition("Социальный работник");
            userRepository.save(socialWorker);

            // (Опционально) Создаём тестового дефектолога
            User defectologist = new User();
            defectologist.setUsername("defect");
            defectologist.setPassword(passwordEncoder.encode(" "));
            defectologist.setRole(Role.DEFECTOLOGIST);
            defectologist.setSurname("Сидорова");
            defectologist.setName("Елена");
            defectologist.setPatronymic("Владимировна");
            defectologist.setPosition("Дефектолог");
            userRepository.save(defectologist);
        }
    }
}