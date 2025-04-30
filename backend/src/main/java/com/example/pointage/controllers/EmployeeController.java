package com.example.pointage.controllers;

import com.example.pointage.entities.Employee;
import com.example.pointage.repositories.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/by-matricule/{matricule}")
    public ResponseEntity<Employee> getEmployeeByMatricule(@PathVariable String matricule) {
        Employee employee = employeeRepository.findByMatricule(matricule)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return ResponseEntity.ok(employee);
    }
}
