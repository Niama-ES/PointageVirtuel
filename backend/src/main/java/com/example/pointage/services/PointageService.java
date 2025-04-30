package com.example.pointage.services;


import com.example.pointage.entities.*;
import com.example.pointage.repositories.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PointageService {
    private final PointageRepository pointageRepository;
    private final EmployeeRepository employeeRepository;
    private final CycleHoraireRepository cycleHoraireRepository;
    private final CycleHoraireParEmployeeRepository cycleHoraireParEmployeeRepository;
    private final CycleHoraireChoisiRepository cycleHoraireChoisiRepository;

    public PointageService(PointageRepository pointageRepository,
                           EmployeeRepository employeeRepository,
                           CycleHoraireRepository cycleHoraireRepository,
                           CycleHoraireParEmployeeRepository cycleHoraireParEmployeeRepository,
                           CycleHoraireChoisiRepository cycleHoraireChoisiRepository) {
        this.pointageRepository = pointageRepository;
        this.employeeRepository = employeeRepository;
        this.cycleHoraireRepository = cycleHoraireRepository;
        this.cycleHoraireParEmployeeRepository = cycleHoraireParEmployeeRepository;
        this.cycleHoraireChoisiRepository = cycleHoraireChoisiRepository;
    }

    public void savePointage(PointageDTO pointageDTO) {
        try {
            // Fetch the employee using the matricule
            Employee employee = employeeRepository.findByMatricule(pointageDTO.getMatricule())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));


            // Fetch the association that matches the given date, cycleHoraire, and employee
            Long cycleId = Long.parseLong(pointageDTO.getCycleHoraire());
            CycleHoraire cycleHoraire = cycleHoraireRepository.findById(cycleId)
                    .orElseThrow(() -> new RuntimeException("CycleHoraire not found"));


            Optional<CycleHoraireParEmployee> associationOpt = cycleHoraireParEmployeeRepository.findByEmployeeAndCycleHoraireAndDateRange(
                    employee, cycleHoraire, pointageDTO.getDate()
            );

            CycleHoraireParEmployee association = associationOpt
                    .orElseThrow(() -> new RuntimeException("No valid association found for the provided data"));

            // Save the cycle_horaire in cycle_horaire_choisi table
            CycleHoraireChoisi cycleHoraireChoisi = new CycleHoraireChoisi();
            cycleHoraireChoisi.setEmployee(employee);
            cycleHoraireChoisi.setCycleHoraire(cycleHoraire);
            cycleHoraireChoisi.setDate_choisi(pointageDTO.getDate());
            cycleHoraireChoisi.setDescription(cycleHoraire.getDescription());

            cycleHoraireChoisiRepository.save(cycleHoraireChoisi);

            // Map PointageDTO to Pointage entity and save to the database
            Pointage pointage = new Pointage();
            pointage.setEmployee(employee);
            pointage.setCycleHoraire(cycleHoraire);
            pointage.setDateP(pointageDTO.getDate());
            pointage.setTimeP(pointageDTO.getTime());
            pointage.setAction(pointageDTO.getAction());

            // Save to the database
            pointageRepository.save(pointage);
        } catch (Exception e) {
            // Log the exception for debugging
            System.out.println("Error during pointage creation: " + e.getMessage());
            throw new RuntimeException("Error saving pointage: " + e.getMessage());
        }
    }

    //Finding the type of the last pointage
    public Optional<Pointage> getLastPointageByEmployee(String matricule) {
        return pointageRepository.findFirstByEmployeeMatriculeOrderByDatePDescTimePDesc(matricule);
    }

}
