package br.com.homeenergy.deviceservice.dto;

import br.com.homeenergy.deviceservice.model.DeviceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DeviceRequestDTO {

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotNull
    private DeviceType type;

    @NotBlank
    @Size(max = 255)
    private String location;

    @NotNull
    @Positive
    private Long userId;
}
