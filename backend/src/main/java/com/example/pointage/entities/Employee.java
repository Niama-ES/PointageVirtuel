package com.example.pointage.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;


import java.util.List;

@Entity
@JsonIgnoreProperties({"associations"})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employee_id;
    private String matricule;
    private String nom;
    private String prenom;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Pointage> pointages;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<CycleHoraireParEmployee> associations;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<CycleHoraireChoisi> cycles_choisi;

    //@ManyToOne
    //@JoinColumn(name = "cycle_horaire_choisi_id", nullable = false) // FK column in Employee table
    //private CycleHoraireChoisi cycleHoraireChoisi;


    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public List<Pointage> getPointages() {
        return pointages;
    }

    public void setPointages(List<Pointage> pointages) {
        this.pointages = pointages;
    }

    public List<CycleHoraireParEmployee> getAssociations() {
        return associations;
    }

    public void setAssociations(List<CycleHoraireParEmployee> associations) {
        this.associations = associations;
    }

    public List<CycleHoraireChoisi> getCycles_choisi() {
        return cycles_choisi;
    }

    public void setCycles_choisi(List<CycleHoraireChoisi> cycles_choisi) {
        this.cycles_choisi = cycles_choisi;
    }
}
