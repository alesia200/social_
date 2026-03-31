package com.example.social.model;

public class ScheduleViewDto {
    private String defectologistName;
    private String childName;
    private String date;
    private String time;
    private String lessonType;

    // Конструктор для удобного создания тестовых данных
    public ScheduleViewDto(String defectologistName, String childName, String date, String time, String lessonType) {
        this.defectologistName = defectologistName;
        this.childName = childName;
        this.date = date;
        this.time = time;
        this.lessonType = lessonType;
    }

    // Геттеры (обязательны, чтобы Thymeleaf мог получить данные)
    public String getDefectologistName() { return defectologistName; }
    public String getChildName() { return childName; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getLessonType() { return lessonType; }
}