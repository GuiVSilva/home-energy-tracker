package br.com.homeenergy.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDTO {
    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 100)
    private String surname;

    @NotBlank
    @Email
    @Size(max = 255)
    private String email;

    private String address;

    private boolean alerting;

    @DecimalMin(value = "0.0", inclusive = true)
    private double energyAlertingThreshold;
}
