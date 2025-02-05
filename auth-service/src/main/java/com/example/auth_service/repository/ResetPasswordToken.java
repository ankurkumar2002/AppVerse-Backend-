package com.example.auth_service.repository;

import java.time.Instant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.auth_service.model.ForgotPasswordDTO;

@Repository
public interface ResetPasswordToken extends JpaRepository<ForgotPasswordDTO,Long> {
    ForgotPasswordDTO findByToken(String token);

    void deleteByExpirationTimeBefore(Instant now);
}
