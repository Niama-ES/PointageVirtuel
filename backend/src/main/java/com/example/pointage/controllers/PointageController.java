package com.example.pointage.controllers;

import com.example.pointage.entities.Pointage;
import com.example.pointage.entities.PointageDTO;
import com.example.pointage.services.PointageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/pointage")
public class PointageController {

    private final PointageService pointageService;

    public PointageController(PointageService pointageService) {
        this.pointageService = pointageService;
    }

    //creation et enregistrement du pointage
    @PostMapping("/create")
    public ResponseEntity<String> createPointage(@RequestBody PointageDTO pointageDTO) {
        pointageService.savePointage(pointageDTO);
        return ResponseEntity.ok("Pointage saved successfully!");
    }

    //trouver le type du dernier pointage
    @GetMapping("/last/{matricule}")
    public ResponseEntity<?> getLastPointage(@PathVariable String matricule) {
        Optional<Pointage> lastPointage = pointageService.getLastPointageByEmployee(matricule);
        if (lastPointage.isPresent()) {
            // Extract and return only the action field
            String lastType = lastPointage.get().getAction(); // Assuming getAction() returns "E" or "S"
            return ResponseEntity.ok(Map.of("lastType", lastType));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No pointage found for this employee.");
        }
    }


    @GetMapping("/test")
    public ResponseEntity<String> createPointage() {
             return ResponseEntity.ok("Pointage saved successfully!");
    }

}

