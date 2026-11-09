package br.com.homeenergy.userservice.service;


import br.com.homeenergy.userservice.dto.UserRequestDTO;
import br.com.homeenergy.userservice.dto.UserResponseDTO;
import br.com.homeenergy.userservice.entity.User;
import br.com.homeenergy.userservice.exception.UserEmailAlreadyExistsException;
import br.com.homeenergy.userservice.exception.UserNotFoundException;
import br.com.homeenergy.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO createUser(UserRequestDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new UserEmailAlreadyExistsException(userDTO.getEmail());
        }

        final User createdUser = User.builder()
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .email(userDTO.getEmail())
                .address(userDTO.getAddress())
                .alerting(userDTO.isAlerting())
                .energyAlertingThreshold(userDTO.getEnergyAlertingThreshold())
                .build();

        User saved = userRepository.save(createdUser);
        return toDTO(saved);
    }

    public UserResponseDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public void updateUser(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        if (userRepository.existsByEmailAndIdNot(dto.getEmail(), id)) {
            throw new UserEmailAlreadyExistsException(dto.getEmail());
        }

        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());
        user.setAddress(dto.getAddress());
        user.setAlerting(dto.isAlerting());
        user.setEnergyAlertingThreshold(dto.getEnergyAlertingThreshold());

        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
    }

    private UserResponseDTO toDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .address(user.getAddress())
                .alerting(user.isAlerting())
                .energyAlertingThreshold(user.getEnergyAlertingThreshold())
                .build();
    }
}
