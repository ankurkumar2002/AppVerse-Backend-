package com.example.auth_service.model;

import java.time.LocalDateTime;

public class UserResponse {

    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private String role;
    private LocalDateTime created_at;

    

    public UserResponse() {
    }

    public UserResponse(String username, String first_name, String last_name, String email, String role, LocalDateTime created_at) {
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.role = role;
        this.created_at = created_at;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

}
