package com.example.social.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class JournalRowDto {
    private int rowNum;
    private LocalDate sessionDate;
    private LocalTime startTime;
    private String serviceDirection;
    private String specialistName;
    private String status;
    private String topicTheme;
    private String specialistNotes;
    private String homework;

    public JournalRowDto(ClassSession session) {
        this.sessionDate = session.getSessionDate();
        this.startTime = session.getStartTime();
        this.status = session.getStatus();
        this.topicTheme = session.getTopicTheme();
        this.specialistNotes = session.getSpecialistNotes();
        if (session.getDefectologist() != null) {
            this.specialistName = session.getDefectologist().getSurname() + " " + session.getDefectologist().getName();
        }
        // serviceDirection и homework пока останутся пустыми, если не заполняются
        this.serviceDirection = "";
        this.homework = "";
    }

    // Геттеры
    public int getRowNum() { return rowNum; }
    public void setRowNum(int rowNum) { this.rowNum = rowNum; }
    public LocalDate getSessionDate() { return sessionDate; }
    public LocalTime getStartTime() { return startTime; }
    public String getServiceDirection() { return serviceDirection; }
    public String getSpecialistName() { return specialistName; }
    public String getStatus() { return status; }
    public String getTopicTheme() { return topicTheme; }
    public String getSpecialistNotes() { return specialistNotes; }
    public String getHomework() { return homework; }
}