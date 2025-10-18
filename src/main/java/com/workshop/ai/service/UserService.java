package com.workshop.ai.service;

import com.workshop.ai.dto.UserDTO;
import com.workshop.ai.entity.User;
import com.workshop.ai.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO saveUser(UserDTO user) {
        final User savedUser = userRepository.save(mapToEntity(user));
        return mapToDTO(savedUser);
    }

    protected User findUserById(String id) {
        return userRepository.findById(UUID.fromString(id)).orElse(null);
    }

    public UserDTO getUserById(String id) {
        final User user = findUserById(id);
        return user != null ? mapToDTO(user) : null;
    }

    public List<UserDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public void deleteUser(String id) {
        userRepository.deleteById(UUID.fromString(id));
    }

    private UserDTO mapToDTO(User user) {
        return new UserDTO(
                user.getId().toString(),
                user.getName()
        );
    }

    private User mapToEntity(UserDTO userDTO) {
        return new User(userDTO.name());
    }
}
