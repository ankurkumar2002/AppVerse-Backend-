package com.example.auth_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.auth_service.model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

    Users findByUsername(String username);

    Optional<Users> findByEmail(String email);
    
}
