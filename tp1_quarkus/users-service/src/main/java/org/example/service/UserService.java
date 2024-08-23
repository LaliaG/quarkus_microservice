package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import org.example.dto.UserDTO;
import org.example.entity.User;
import org.example.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        return userRepository.listAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        return userRepository.findByIdOptional(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new WebApplicationException("User not found", 404));
    }

    public void addUser(UserDTO userDTO) {
        User user = mapToEntity(userDTO);
        userRepository.persist(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO mapToDto(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    private User mapToEntity(UserDTO userDTO) {
        return User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .build();
    }
}
