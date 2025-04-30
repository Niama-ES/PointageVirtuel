
package com.example.pointage.entities;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class CycleHoraireChoisi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cycle_choisi_id;
    private String description;
    private LocalDate date_choisi;


    //@OneToMany(mappedBy = "cycleHoraireChoisi", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Employee> employees;

    @ManyToOne
    @JoinColumn(name = "cycle_id", nullable = false)
    private CycleHoraire cycleHoraire;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    //getters and setters


    public Long getCycle_choisi_id() {
        return cycle_choisi_id;
    }

    public void setCycle_choisi_id(Long cycle_choisi_id) {
        this.cycle_choisi_id = cycle_choisi_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate_choisi() {
        return date_choisi;
    }

    public void setDate_choisi(LocalDate date_choisi) {
        this.date_choisi = date_choisi;
    }

    public CycleHoraire getCycleHoraire() {
        return cycleHoraire;
    }

    public void setCycleHoraire(CycleHoraire cycleHoraire) {
        this.cycleHoraire = cycleHoraire;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
