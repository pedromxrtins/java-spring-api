package com.projetointegrador.api.repository;

import com.projetointegrador.api.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
}
