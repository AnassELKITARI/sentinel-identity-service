package com.sentinel.identity.service;

import com.sentinel.identity.entity.UserIdentity;
import com.sentinel.identity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IdentityService {

    private final UserRepository userRepository;

    public UserIdentity registerUser(UserIdentity user) {
        // Check if email is already taken
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Validation Error: Email already registered in the system.");
        }
        return userRepository.save(user);
    }

    public boolean validateCertificate(String serialNumber) {
        Optional<UserIdentity> user = userRepository.findByCertSerialNumber(serialNumber);

        // Certificate is valid only if it exists in DB and the user is active
        return user.map(UserIdentity::isActive).orElse(false);
    }
}