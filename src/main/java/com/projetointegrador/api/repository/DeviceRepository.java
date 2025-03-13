package com.projetointegrador.api.repository;

import com.projetointegrador.api.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
