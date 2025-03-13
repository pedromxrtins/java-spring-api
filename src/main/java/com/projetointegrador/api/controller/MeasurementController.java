package com.projetointegrador.api.controller;

import com.projetointegrador.api.model.Measurement;
import com.projetointegrador.api.repository.MeasurementRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementRepository measurementRepository;

    public MeasurementController(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @GetMapping
    public List<Measurement> getAllMeasurements() {
        return measurementRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Measurement> getMeasurementById(@PathVariable Long id) {
        return measurementRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
