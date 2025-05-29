package com.projetointegrador.api.controller;

import com.projetointegrador.api.model.Device;
import com.projetointegrador.api.model.User;
import com.projetointegrador.api.repository.DeviceRepository;
import com.projetointegrador.api.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;

    public DeviceController(DeviceRepository deviceRepository, UserRepository userRepository) {
        this.deviceRepository = deviceRepository;
        this.userRepository = userRepository;
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
    public ResponseEntity<Device> createDevice(@RequestBody Device device) {
    
        if (device.getUser() != null && device.getUser().getId() != null) {
            User user = userRepository.findById(device.getUser().getId()).orElse(null);
            device.setUser(user);
        }

        device.setLastUpdated(LocalDateTime.now());

        Device saved = deviceRepository.save(device);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable Long id, @RequestBody Device updatedDevice) {
        return deviceRepository.findById(id)
                .map(existingDevice -> {
                    existingDevice.setDeviceName(updatedDevice.getDeviceName());
                    existingDevice.setState(updatedDevice.getState());

                    if (updatedDevice.getUser() != null && updatedDevice.getUser().getId() != null) {
                        User user = userRepository.findById(updatedDevice.getUser().getId()).orElse(null);
                        existingDevice.setUser(user);
                    } else {
                        existingDevice.setUser(null);
                    }

                    existingDevice.setLastUpdated(LocalDateTime.now());

                    Device saved = deviceRepository.save(existingDevice);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
