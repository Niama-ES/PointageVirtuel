package com.example.pointage.entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class PointageDTO {
    private String matricule;
    private String cycleHoraire;
    private LocalDate date;
    private LocalTime time;
    private String action;

    // Getters and Setters


    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getCycleHoraire() {
        return cycleHoraire;
    }

    public void setCycleHoraire(String cycleHoraire) {
        this.cycleHoraire = cycleHoraire;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}

