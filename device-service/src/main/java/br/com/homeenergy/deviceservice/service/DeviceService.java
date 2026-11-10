package br.com.homeenergy.deviceservice.service;


import br.com.homeenergy.deviceservice.dto.DeviceRequestDTO;
import br.com.homeenergy.deviceservice.dto.DeviceResponseDTO;
import br.com.homeenergy.deviceservice.entity.Device;
import br.com.homeenergy.deviceservice.exception.DeviceNotFoundException;
import br.com.homeenergy.deviceservice.repository.DeviceRepository;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    private DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public DeviceResponseDTO createDevice(DeviceRequestDTO deviceRequestDTO) {
        final Device createdDevice = Device.builder()
                .name(deviceRequestDTO.getName())
                .type(deviceRequestDTO.getType())
                .location(deviceRequestDTO.getLocation())
                .userId(deviceRequestDTO.getUserId())
                .build();

        Device saved = deviceRepository.save(createdDevice);
        return toDTO(saved);
    }

    public DeviceResponseDTO getDeviceById(Long id) {
        return deviceRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new DeviceNotFoundException(id));
    }

    public DeviceResponseDTO updateDevice(Long id, DeviceRequestDTO deviceRequestDTO){
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException(id));

        device.setName(deviceRequestDTO.getName());
        device.setType(deviceRequestDTO.getType());
        device.setLocation(deviceRequestDTO.getLocation());
        device.setUserId(deviceRequestDTO.getUserId());

        final Device updatedDevice = deviceRepository.save(device);
        return toDTO(updatedDevice);
    }

    public void deleteDevice(Long id) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException(id));
        deviceRepository.delete(device);
    }


    private DeviceResponseDTO toDTO(Device device) {
        return DeviceResponseDTO.builder()
                .id(device.getId())
                .name(device.getName())
                .type(device.getType())
                .location(device.getLocation())
                .userId(device.getUserId())
                .build();
    }
}
