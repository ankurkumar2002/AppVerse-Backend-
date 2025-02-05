package com.example.auth_service.model;

import java.sql.Time;
import java.time.Instant;

import io.github.resilience4j.core.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "password_reset_tokens")
public class ForgotPasswordDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(nullable = false)
    private Instant expirationTime;
    
    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    

    public ForgotPasswordDTO() {
    }

    public ForgotPasswordDTO(Long id, String token, Users user, Instant expirationTime, Instant createdAt) {
        this.id = id;
        this.token = token;
        this.user = user;
        this.expirationTime = expirationTime;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Instant getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Instant expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isValid(String token) {
        return this.token.equals(token) && this.expirationTime.isAfter(Instant.now());
    }
    
   
    

}
