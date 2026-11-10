package br.com.homeenergy.deviceservice.controller;

import br.com.homeenergy.deviceservice.dto.DeviceRequestDTO;
import br.com.homeenergy.deviceservice.dto.DeviceResponseDTO;
import br.com.homeenergy.deviceservice.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/device")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    public ResponseEntity<DeviceResponseDTO> createDevice(@Valid @RequestBody DeviceRequestDTO deviceRequestDTO) {
        DeviceResponseDTO created = deviceService.createDevice(deviceRequestDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceResponseDTO> getDeviceById(@PathVariable Long id) {
        return ResponseEntity.ok(deviceService.getDeviceById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceResponseDTO> updateDevice(@PathVariable Long id, @Valid @RequestBody DeviceRequestDTO deviceRequestDTO) {
        DeviceResponseDTO updated = deviceService.updateDevice(id, deviceRequestDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }

}
