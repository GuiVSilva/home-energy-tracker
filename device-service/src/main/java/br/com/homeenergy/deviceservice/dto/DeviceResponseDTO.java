package br.com.homeenergy.deviceservice.dto;

import br.com.homeenergy.deviceservice.model.DeviceType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeviceResponseDTO {
    private long id;
    private String name;
    private DeviceType type;
    private String location;
    private long userId;
}
