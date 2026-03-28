package com.example.social.config;

import com.example.social.model.Child;
import com.example.social.model.Gender;
import com.example.social.model.Role;
import com.example.social.model.User;
import com.example.social.repository.ChildRepository;
import com.example.social.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChildRepository childRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {
            // администратор
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode(" ")); // пароль admin
            admin.setRole(Role.ADMIN);
            admin.setSurname("Администратор");
            admin.setName("Системы");
            admin.setPatronymic(""); // или можно оставить null
            admin.setPosition("Администратор");
            userRepository.save(admin);

            // соц. работник
            User socialWorker = new User();
            socialWorker.setUsername("social");
            socialWorker.setPassword(passwordEncoder.encode(" "));
            socialWorker.setRole(Role.SOCIAL_WORKER);
            socialWorker.setSurname("Петрова");
            socialWorker.setName("Анна");
            socialWorker.setPatronymic("Ивановна");
            socialWorker.setPosition("Социальный работник");
            userRepository.save(socialWorker);

            // дефектолог
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
        if (childRepository.count() == 0) {
            Child child1 = new Child();
            child1.setSurname("Иванов");
            child1.setName("Иван");
            child1.setPatronymic("Иванович");
            child1.setBirthDate(LocalDate.of(2015, 5, 12));
            child1.setGender(Gender.МУЖ);
            child1.setSnils("12345678901");
            child1.setActualAddress("г. Москва, ул. Ленина, д. 10, кв. 5");
            child1.setRegistrationAddress("г. Москва, ул. Ленина, д. 10, кв. 5");
            child1.setDocSeries("4510");
            child1.setDocNumber("123456");
            child1.setRepresentativePhone("+79161234567");
            child1.setRepresentativeRelation("отец");
            child1.setRepresentativeFio("Иванов Иван Иванович");
            childRepository.save(child1);

            Child child2 = new Child();
            child2.setSurname("Петрова");
            child2.setName("Мария");
            child2.setPatronymic("Алексеевна");
            child2.setBirthDate(LocalDate.of(2018, 8, 23));
            child2.setGender(Gender.ЖЕН);
            child2.setSnils("98765432109");
            child2.setActualAddress("г. Санкт-Петербург, Невский пр., д. 25");
            child2.setRegistrationAddress("г. Санкт-Петербург, Невский пр., д. 25");
            child2.setDocSeries("4020");
            child2.setDocNumber("789012");
            child2.setRepresentativePhone("89211234567");
            child2.setRepresentativeRelation("мать");
            child2.setRepresentativeFio("Петрова Анна Сергеевна");
            childRepository.save(child2);

            Child child3 = new Child();
            child3.setSurname("Смирнов");
            child3.setName("Алексей");
            child3.setPatronymic("Дмитриевич");
            child3.setBirthDate(LocalDate.of(2010, 3, 15));
            child3.setGender(Gender.МУЖ);
            child3.setSnils("55544433322");
            child3.setActualAddress("г. Екатеринбург, ул. Мамина-Сибиряка, д. 7");
            child3.setRegistrationAddress("г. Екатеринбург, ул. Мамина-Сибиряка, д. 7");
            child3.setDocSeries("6611");
            child3.setDocNumber("456789");
            child3.setRepresentativePhone("79531234567");
            child3.setRepresentativeRelation("отец");
            child3.setRepresentativeFio("Смирнов Дмитрий Игоревич");
            childRepository.save(child3);
        }
    }
}