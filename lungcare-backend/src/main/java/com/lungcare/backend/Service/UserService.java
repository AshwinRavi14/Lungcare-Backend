package com.lungcare.backend.Service;

import com.lungcare.backend.DTO.UserRequestDTO;
import com.lungcare.backend.Entity.User;
import com.lungcare.backend.Repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }
    public User createUser (UserRequestDTO dto)
    {
        Optional<User> existingUser = userRepository.findByUsername(dto.getUsername());
        if(existingUser.isPresent())
        {
            throw new RuntimeException("Username Already Exists");
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setEmail(dto.getEmail());
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() -> new RuntimeException("user not found" + id));
    }

    public User updateUser(Long id, @Valid UserRequestDTO dto) {
        User user = getUserById(id);
        user.setUsername(dto.getUsername());
        user.setRole(dto.getRole());
        user.setEmail(dto.getEmail());
        if (dto.getPassword() != null & !dto.getPassword().isEmpty())
        {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if(!userRepository.existsById(id))
        {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }
}
