package com.gustavo.userapi.services;

import com.gustavo.userapi.dto.UserDTO;
import com.gustavo.userapi.entities.User;
import com.gustavo.userapi.repositories.UserRepository;
import com.gustavo.userapi.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDTO findById(String userId) {

        User userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("ID not found: " + userId));

        UserDTO userDTO = UserDTO.builder()
                .id(userEntity.getId())
                .cpf(userEntity.getCpf())
                .email(userEntity.getEmail())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .build();

        return userDTO;
    }
}
