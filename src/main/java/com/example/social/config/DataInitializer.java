package com.example.social.config;

import com.example.social.model.*;
import com.example.social.repository.ChildRepository;
import com.example.social.repository.IcfCategoryRepository;
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
    private IcfCategoryRepository icfCategoryRepository;
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
        // === ИНИЦИАЛИЗАЦИЯ СПРАВОЧНИКА МКФ ===
        if (icfCategoryRepository.count() == 0) {
            initIcfCategories();
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
            course1.setStatus("active");
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
            course2.setStatus("active");
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
            course3.setStatus("active");
            course3.setEffectivenessPercentage(null);
            courseRepository.save(course3);
        }

    }
    // ==========================================
    // Вспомогательный метод для заполнения МКФ
    // ==========================================
    private void initIcfCategories() {
        // Параметры: (Код, Название, 0-3, 4-7, 8-11, 12-17)
        // true = исследуется, false = ставится "X" (не исследуется)

        addCategory("d230", "Выполнение повседневного распорядка", true, true, true, true);
        addCategory("d2301", "Организация повседневного распорядка", true, true, true, true);

        addCategory("d410", "Изменение позы тела", true, true, true, true);
        addCategory("d4100", "Изменение позы при положении лежа", true, true, true, true);
        addCategory("d4103", "Изменение позы при положении сидя", true, true, true, true);
        addCategory("d4104", "Изменение позы при положении стоя", true, true, true, true);
        addCategory("d4105", "Наклон", true, true, true, true);

        addCategory("d415", "Поддержание положения тела", true, true, true, true);
        addCategory("d4150", "Нахождение в положении лежа", true, true, true, true);
        addCategory("d4153", "Нахождение в положении сидя", true, true, true, true);
        addCategory("d4154", "Нахождение в положении стоя", true, true, true, true);

        addCategory("d420", "Перемещение тела", true, true, true, true);
        addCategory("d4200", "Перемещение тела в положении сидя", true, true, true, true);
        addCategory("d4201", "Перемещение тела в положении лежа", true, true, true, true);

        addCategory("d430", "Поднятие и перенос объектов", true, true, true, true);
        addCategory("d4300", "Поднятие", true, true, true, true);
        addCategory("d4301", "Перенос кистями рук", true, true, true, true);
        addCategory("d4302", "Перенос руками", true, true, true, true);
        addCategory("d4303", "Перенос на плечах, бедрах и спине", false, false, false, true); // X для 0-3, 4-7, 8-11
        addCategory("d4305", "Опускание объектов", true, true, true, true);

        addCategory("d435", "Перемещение объектов ногами", true, true, true, true);
        addCategory("d4350", "Толкание ногами", true, true, true, true);
        addCategory("d4351", "Удар ногой", true, true, true, true);

        addCategory("d440", "Использование точных движений кисти", true, true, true, true);
        addCategory("d4400", "Подбирание", true, true, true, true);
        addCategory("d4401", "Захват", true, true, true, true);
        addCategory("d4402", "Манипулирование (пальцами и кистями рук)", true, true, true, true);
        addCategory("d4403", "Отпускание", true, true, true, true);

        addCategory("d445", "Использование кисти и руки", true, true, true, true);
        addCategory("d4450", "Притягивание (объекта к себе)", true, true, true, true);
        addCategory("d4451", "Отталкивание (объекта от себя)", true, true, true, true);
        addCategory("d4452", "Вытягивание (рук, чтобы достать что-либо)", true, true, true, true);
        addCategory("d4453", "Вращение или сгибание кистями или руками", true, true, true, true);
        addCategory("d4454", "Бросание", true, true, true, true);
        addCategory("d4455", "Хватание", true, true, true, true);

        addCategory("d450", "Ходьба", true, true, true, true);
        addCategory("d4500", "Ходьба на короткие расстояния (менее километра)...", true, true, true, true);

        addCategory("d510", "Мытье", true, true, true, true);
        addCategory("d5100", "Мытье частей тела", true, true, true, true);
        addCategory("d5101", "Мытье всего тела", false, false, true, true); // X X
        addCategory("d5102", "Вытирание и сушка", false, false, true, true); // X X

        addCategory("d520", "Уход за частями тела", true, true, true, true);
        addCategory("d5200", "Уход за кожей", false, false, true, true); // X X
        addCategory("d5201", "Уход за полостью рта", false, true, true, true); // X
        addCategory("d5202", "Уход за волосами", false, true, true, true); // X
        addCategory("d5203", "Уход за ногтями на руках", false, false, true, true); // X X
        addCategory("d5204", "Уход за ногтями на ногах", false, false, false, true); // X X X

        addCategory("d530", "Физиологические отправления", true, true, true, true);
        addCategory("d5300", "Регуляция мочеиспускания", true, true, true, true);
        addCategory("d5301", "Регуляция дефекации", true, true, true, true);

        addCategory("d540", "Одевание", true, true, true, true);
        addCategory("d5400", "Надевание одежды", true, true, true, true);
        addCategory("d5401", "Снятие одежды", true, true, true, true);
        addCategory("d5402", "Надевание или снятие с нижних конечностей", false, true, true, true); // X
        addCategory("d5403", "Снятие с нижних конечностей", true, true, true, true); // Без X по тексту
        addCategory("d5404", "Выбор соответствующей одежды", false, true, true, true); // X

        addCategory("d550", "Прием пищи", true, true, true, true);
        addCategory("d560", "Питье", true, true, true, true);

        addCategory("d570", "Забота о своем здоровье", true, true, true, true);
        addCategory("d5701", "Соблюдение диеты и здорового образа жизни", false, false, true, true); // X X

        addCategory("d620", "Приобретение товаров и услуг", true, true, true, true);
        addCategory("d6200", "Осуществление покупок", false, false, true, true); // X X

        addCategory("d630", "Приготовление пищи", true, true, true, true);
        addCategory("d6300", "Приготовление простых блюд", false, true, true, true); // X
        addCategory("d6301", "Приготовление сложных блюд", false, false, false, true); // X X X

        addCategory("d640", "Выполнение работы по дому", true, true, true, true);
        addCategory("d6400", "Стирка и сушка белья и одежды", false, false, true, true); // X X
        addCategory("d6401", "Уборка на кухне и мытье посуды", false, false, true, true); // X X
        addCategory("d6402", "Уборка жилой части", false, false, true, true); // X X
        addCategory("d6403", "Использование бытовой техники", false, true, true, true); // X
        addCategory("d6405", "Удаление мусора", false, false, true, true); // X X
    }

        private void addCategory(String code, String name, boolean isAge03, boolean isAge47, boolean isAge811, boolean isAge1217) {
        IcfCategory category = new IcfCategory();
        category.setCodeMKF(code);
        category.setName(name);
        category.setIsAge03(isAge03);
        category.setIsAge47(isAge47);
        category.setIsAge811(isAge811);
        category.setIsAge1217(isAge1217);
        icfCategoryRepository.save(category);
    }
}