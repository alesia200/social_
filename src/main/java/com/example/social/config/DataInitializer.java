package com.example.social.config;

import com.example.social.model.*;
import com.example.social.repository.ChildRepository;
import com.example.social.repository.RehabilitationCourseRepository;
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
    private RehabilitationCourseRepository courseRepository;
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
        // === Инициализация детей и их курсов ===
        if (childRepository.count() == 0) {
            // Ребёнок 1
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

            DisabilityInfo dis1 = new DisabilityInfo();
            dis1.setDisabilityGroup("Ребенок-инвалид");
            dis1.setIsFirstTime(true);
            dis1.setEstablishmentDate(LocalDate.of(2023, 1, 15));
            dis1.setExpiryDate(LocalDate.of(2027, 9, 14));
            dis1.setCause("Расстройство аутистического спектра");
            dis1.setIpraNumber("0000.00.00/2022");
            dis1.setIpraExpiryDate(LocalDate.of(2027, 10, 14));
            dis1.setRehabPotential("II степень");
            dis1.setRehabPrognosis("Относительно благоприятный");
            dis1.setIsPalliative(false);
            child1.setDisabilityInfo(dis1);
            childRepository.save(child1);

            // Курс для ребёнка 1
            RehabilitationCourse course1 = new RehabilitationCourse();
            course1.setChild(child1);
            course1.setStartDate(LocalDate.of(2026, 3, 1));
            course1.setEndDate(LocalDate.of(2026, 5, 31));
            course1.setContractNumber("Д-001");
            course1.setStatus("Завершён");
            course1.setEffectivenessPercentage(null); // будет рассчитан позже
            courseRepository.save(course1);

            // Ребёнок 2
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

            DisabilityInfo dis2 = new DisabilityInfo();
            dis2.setDisabilityGroup("Ребенок-инвалид");
            dis2.setIsFirstTime(false);
            dis2.setEstablishmentDate(LocalDate.of(2022, 5, 10));
            dis2.setExpiryDate(LocalDate.of(2027, 7, 9));
            dis2.setCause("Расстройство аутистического спектра");
            dis2.setIpraNumber("0000.00.00/2022");
            dis2.setIpraExpiryDate(LocalDate.of(2027, 5, 9));
            dis2.setRehabPotential("III степень");
            dis2.setRehabPrognosis("Сомнительный");
            dis2.setIsPalliative(false);
            child2.setDisabilityInfo(dis2);
            childRepository.save(child2);

            // Курс для ребёнка 2
            RehabilitationCourse course2 = new RehabilitationCourse();
            course2.setChild(child2);
            course2.setStartDate(LocalDate.of(2026, 2, 1));
            course2.setEndDate(LocalDate.of(2026, 4, 30));
            course2.setContractNumber("Д-002");
            course2.setStatus("Активен");
            course2.setEffectivenessPercentage(null);
            courseRepository.save(course2);

            // Ребёнок 3
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

            DisabilityInfo dis3 = new DisabilityInfo();
            dis3.setDisabilityGroup("1");
            dis3.setIsFirstTime(true);
            dis3.setEstablishmentDate(LocalDate.of(2024, 2, 20));
            dis3.setExpiryDate(LocalDate.of(2027, 2, 19));
            dis3.setCause("Расстройство аутистического спектра");
            dis3.setIpraNumber("0000.00.00/2022");
            dis3.setIpraExpiryDate(LocalDate.of(2027, 2, 19));
            dis3.setRehabPotential("I степень");
            dis3.setRehabPrognosis("Благоприятный");
            dis3.setIsPalliative(false);
            child3.setDisabilityInfo(dis3);
            childRepository.save(child3);

            // Курс для ребёнка 3
            RehabilitationCourse course3 = new RehabilitationCourse();
            course3.setChild(child3);
            course3.setStartDate(LocalDate.of(2026, 2, 1));
            course3.setEndDate(LocalDate.of(2026, 4, 30));
            course3.setContractNumber("Д-003");
            course3.setStatus("Завершён");
            course3.setEffectivenessPercentage(null);
            courseRepository.save(course3);
        }
    }
}