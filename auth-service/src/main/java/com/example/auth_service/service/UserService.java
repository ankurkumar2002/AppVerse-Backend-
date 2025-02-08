package com.example.auth_service.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.auth_service.model.LoginDTO;
import com.example.auth_service.model.UserResponse;
import com.example.auth_service.model.Users;
import com.example.auth_service.repository.UserRepo;

@Service
public class UserService {
    
    @Autowired
    UserRepo userRepo;


    // LoginDTO loginDTO;


    public UserResponse getUserInfoToAuthenticate(String username){
        Users user = userRepo.findByUsername(username);
        
        if(user != null){
            LoginDTO loginDTO = new LoginDTO();
            loginDTO.setUsername(user.getUsername());
            loginDTO.setPassword(user.getPassword());

            UserResponse userResponse = new UserResponse();
            userResponse.setCreated_at(user.getCreated_at());
            userResponse.setEmail(user.getEmail());
            userResponse.setFirst_name(user.getFirst_name());
            userResponse.setLast_name(user.getLast_name());
            userResponse.setRole(user.getRole());
            userResponse.setUsername(user.getUsername());

            return userResponse;
        }

        return null;
    }

    public Users getUserByUsername(String username) {
        Users user = userRepo.findByUsername(username);

        
        return user;
    }

    public Users addUser(Users user){
        return userRepo.save(user);
    }

    public List<Users> getAllUsers(){
        return userRepo.findAll();
    }

    public Optional<Users> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public Users createUserFromOAuth2(OAuth2User oauthUser) {
        Users user = new Users();
        user.setEmail(oauthUser.getAttribute("email"));
        user.setUsername(oauthUser.getAttribute("name")); // Use name or email as username
        user.setPassword(UUID.randomUUID().toString()); // Generate a random password
        user.setRole("USER"); // Default role
        return userRepo.save(user);
    }

    

   
}
