package com.example.pointage.repositories;

import com.example.pointage.entities.CycleHoraireChoisi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CycleHoraireChoisiRepository extends JpaRepository<CycleHoraireChoisi, Long> {
}
