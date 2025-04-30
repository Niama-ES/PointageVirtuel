package com.example.pointage.repositories;

import com.example.pointage.entities.CycleHoraireParEmployee;
import com.example.pointage.entities.Employee;
import com.example.pointage.entities.CycleHoraire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface CycleHoraireParEmployeeRepository extends JpaRepository<CycleHoraireParEmployee, Long> {

    @Query("SELECT cpe FROM CycleHoraireParEmployee cpe " +
            "WHERE cpe.employee = :employee " +
            "AND cpe.cycleHoraire = :cycleHoraire " +
            "AND :date BETWEEN cpe.date_debut AND cpe.date_fin")
    Optional<CycleHoraireParEmployee> findByEmployeeAndCycleHoraireAndDateRange(
            @Param("employee") Employee employee,
            @Param("cycleHoraire") CycleHoraire cycleHoraire,
            @Param("date") LocalDate date);
}

