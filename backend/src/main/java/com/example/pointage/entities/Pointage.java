
package com.example.pointage.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Pointage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PointageID;
    private LocalDate dateP;
    private String Action;
    private LocalTime timeP;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false) // FK column in Pointage table
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "cycle_id", nullable = false)
    private CycleHoraire cycleHoraire;




    //getters and setters


    public Long getPointageID() {
        return PointageID;
    }

    public void setPointageID(Long pointageID) {
        PointageID = pointageID;
    }

    public LocalDate getDateP() {
        return dateP;
    }

    public void setDateP(LocalDate date_p) {
        this.dateP = date_p;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    public LocalTime getTimeP() {
        return timeP;
    }

    public void setTimeP(LocalTime timeP) {
        this.timeP = timeP;
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
