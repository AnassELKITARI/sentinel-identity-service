package com.sentinel.identity.repository;

import com.sentinel.identity.entity.UserIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserIdentity, Long> {


    Optional<UserIdentity> findByCertSerialNumber(String certSerialNumber);

    boolean existsByEmail(String email);
}