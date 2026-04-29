package com.sentinel.identity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "user_identities")
@Data // Generates Getters, Setters, toString, etc. via Lombok
@NoArgsConstructor
@AllArgsConstructor
public class UserIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Full name is required")
    @Column(nullable = false)
    private String fullName;

    @NotBlank(message = "Email is required")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Certificate Serial is required")
    @Size(min = 10, max = 50, message = "Certificate serial must be between 10 and 50 characters")
    @Column(unique = true, nullable = false)
    private String certSerialNumber;

    @Column(nullable = false)
    private boolean isActive = true;
}