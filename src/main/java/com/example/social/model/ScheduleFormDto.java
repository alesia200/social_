package com.example.social.model;

public class ScheduleFormDto {
    private Long id; // Нужен для редактирования
    private Long defectologistId;
    private Long childId;
    private String date;
    private String time;
    private String lessonType;

    // Пустой конструктор нужен для Spring
    public ScheduleFormDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getDefectologistId() { return defectologistId; }
    public void setDefectologistId(Long defectologistId) { this.defectologistId = defectologistId; }
    public Long getChildId() { return childId; }
    public void setChildId(Long childId) { this.childId = childId; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    public String getLessonType() { return lessonType; }
    public void setLessonType(String lessonType) { this.lessonType = lessonType; }
}