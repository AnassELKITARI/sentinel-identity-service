package com.sentinel.identity.controller;

import com.sentinel.identity.entity.UserIdentity;
import com.sentinel.identity.service.IdentityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/identities")
@RequiredArgsConstructor
public class IdentityController {

    private final IdentityService identityService;

    @PostMapping("/register")
    public ResponseEntity<UserIdentity> register(@Valid @RequestBody UserIdentity user) {
        return ResponseEntity.ok(identityService.registerUser(user));
    }

    @GetMapping("/verify/{serialNumber}")
    public ResponseEntity<String> verify(@PathVariable String serialNumber) {
        boolean isValid = identityService.validateCertificate(serialNumber);

        if (isValid) {
            return ResponseEntity.ok("Certificate Verified: Access Granted");
        } else {
            return ResponseEntity.status(403).body("Invalid or Inactive Certificate: Access Denied");
        }
    }
}