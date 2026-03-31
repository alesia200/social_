package com.example.social.model;

public class EffectivenessRecordDto {
    private String childName;
    private double primaryScore;   // Баллы при первичной диагностике
    private double secondaryScore; // Баллы при повторной диагностике

    public EffectivenessRecordDto(String childName, double primaryScore, double secondaryScore) {
        this.childName = childName;
        this.primaryScore = primaryScore;
        this.secondaryScore = secondaryScore;
    }

    public String getChildName() { return childName; }
    public void setChildName(String childName) { this.childName = childName; }
    public double getPrimaryScore() { return primaryScore; }
    public void setPrimaryScore(double primaryScore) { this.primaryScore = primaryScore; }
    public double getSecondaryScore() { return secondaryScore; }
    public void setSecondaryScore(double secondaryScore) { this.secondaryScore = secondaryScore; }
}