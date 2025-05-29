package com.projetointegrador.api.controller;

import com.projetointegrador.api.model.Device;
import com.projetointegrador.api.repository.DeviceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceRepository deviceRepository;

    public DeviceController(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @GetMapping
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Long id) {
        return deviceRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Device createDevice(@RequestBody Device device) {
        // Opcional: setar lastUpdated para agora ao criar
        device.setLastUpdated(LocalDateTime.now());
        return deviceRepository.save(device);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable Long id, @RequestBody Device updatedDevice) {
        return deviceRepository.findById(id)
                .map(existingDevice -> {
                    existingDevice.setDeviceName(updatedDevice.getDeviceName());
                    existingDevice.setState(updatedDevice.getState());
                    existingDevice.setLastUpdated(LocalDateTime.now());
                 

                    Device savedDevice = deviceRepository.save(existingDevice);
                    return ResponseEntity.ok(savedDevice);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
