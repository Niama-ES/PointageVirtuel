package com.example.pointage.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CycleHoraire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cycle_id;
    private String description;

    @OneToMany(mappedBy = "cycleHoraire", cascade = CascadeType.ALL)
    private List<CycleHoraireParEmployee> associations;

    @OneToMany(mappedBy = "cycleHoraire", cascade = CascadeType.ALL)
    private List<Pointage> pointages;

    @OneToMany(mappedBy = "cycleHoraire", cascade = CascadeType.ALL)
    private List<CycleHoraireChoisi> cycles_choisi;
    //getters and setter


    public Long getCycle_id() {
        return cycle_id;
    }

    public void setCycle_id(Long cycle_id) {
        this.cycle_id = cycle_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CycleHoraireParEmployee> getAssociations() {
        return associations;
    }

    public void setAssociations(List<CycleHoraireParEmployee> associations) {
        this.associations = associations;
    }

    public List<Pointage> getPointages() {
        return pointages;
    }

    public void setPointages(List<Pointage> pointages) {
        this.pointages = pointages;
    }

    public List<CycleHoraireChoisi> getCycles_choisi() {
        return cycles_choisi;
    }

    public void setCycles_choisi(List<CycleHoraireChoisi> cycles_choisi) {
        this.cycles_choisi = cycles_choisi;
    }
}
