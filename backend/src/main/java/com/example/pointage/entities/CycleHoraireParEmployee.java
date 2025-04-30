package com.example.pointage.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class CycleHoraireParEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UniqueID;
    private LocalDate date_debut;
    private LocalDate date_fin;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "cycle_id", nullable = false)
    private CycleHoraire cycleHoraire;

    //getters and setters


    public Long getUniqueID() {
        return UniqueID;
    }

    public void setUniqueID(Long uniqueID) {
        UniqueID = uniqueID;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public CycleHoraire getCycleHoraire() {
        return cycleHoraire;
    }

    public void setCycleHoraire(CycleHoraire cycleHoraire) {
        this.cycleHoraire = cycleHoraire;
    }
}
