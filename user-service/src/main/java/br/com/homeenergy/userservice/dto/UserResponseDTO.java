package br.com.homeenergy.userservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String address;
    private boolean alerting;
    private double energyAlertingThreshold;
}
