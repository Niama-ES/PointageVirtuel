package com.example.pointage.repositories;

import com.example.pointage.entities.CycleHoraire;
import com.example.pointage.entities.CycleHoraireChoisi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CycleHoraireRepository extends JpaRepository<CycleHoraire, Long> {
    Optional<CycleHoraire> findById(Long cycle_id);
}
