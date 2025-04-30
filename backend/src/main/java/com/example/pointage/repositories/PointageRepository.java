package com.example.pointage.repositories;

import com.example.pointage.entities.Pointage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PointageRepository extends JpaRepository<Pointage,Long> {
        Optional<Pointage> findFirstByEmployeeMatriculeOrderByDatePDescTimePDesc(String matricule);

}
