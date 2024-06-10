package com.ru.application.service;

import com.ru.application.config.CustomUserDetailsService;
import com.ru.application.model.dto.AuthRequest;
import com.ru.application.model.dto.UserDTO;
import com.ru.application.model.entity.User;
import com.ru.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(UserDTO userDTO) {
        User user = mapToUser(userDTO);
        user.setRegisteredAt(LocalDateTime.now());
        log.info("User was registered{}", user.getEmail());
        return userRepository.save(user);
    }

    public boolean authenticateUser(AuthRequest loginRequest) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.email());
        if (userDetails != null) {
            return passwordEncoder.matches(loginRequest.password(), userDetails.getPassword());
        }
        return false;
    }

    private User mapToUser(UserDTO userDTO) {
        User user = new User();
        user.setAge(userDTO.age());
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setPassword(passwordEncoder.encode(userDTO.password()));
        return user;
    }
}
