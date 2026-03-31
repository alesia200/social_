package com.example.social.model;

public class AttendanceFilterDto {
    private Long courseId;
    private Long specialistId;

    // Генерируйте геттеры и сеттеры (или используйте Lombok @Data)
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
    public Long getSpecialistId() { return specialistId; }
    public void setSpecialistId(Long specialistId) { this.specialistId = specialistId; }
}