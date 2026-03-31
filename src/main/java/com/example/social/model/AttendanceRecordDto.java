package com.example.social.model;

public class AttendanceRecordDto {
    private String childName;
    private int totalLessons;
    private int attended;
    private int missed;

    // Конструктор для удобного создания заглушки
    public AttendanceRecordDto(String childName, int totalLessons, int attended, int missed) {
        this.childName = childName;
        this.totalLessons = totalLessons;
        this.attended = attended;
        this.missed = missed;
    }

    // Геттеры и сеттеры
    public String getChildName() { return childName; }
    public void setChildName(String childName) { this.childName = childName; }
    public int getTotalLessons() { return totalLessons; }
    public void setTotalLessons(int totalLessons) { this.totalLessons = totalLessons; }
    public int getAttended() { return attended; }
    public void setAttended(int attended) { this.attended = attended; }
    public int getMissed() { return missed; }
    public void setMissed(int missed) { this.missed = missed; }
}