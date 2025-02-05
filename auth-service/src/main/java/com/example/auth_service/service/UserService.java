package com.example.auth_service.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.auth_service.model.LoginDTO;
import com.example.auth_service.model.Users;
import com.example.auth_service.repository.UserRepo;

@Service
public class UserService {
    
    @Autowired
    UserRepo userRepo;


    // LoginDTO loginDTO;


    public LoginDTO getUserInfoToAuthenticate(String username){
        Users user = userRepo.findByUsername(username);
        
        if(user != null){
            LoginDTO loginDTO = new LoginDTO();
            loginDTO.setUsername(user.getUsername());
            loginDTO.setPassword(user.getPassword());

            return loginDTO;
        }

        return null;
    }

    public Users getUserByUsername(String username) {
        return userRepo.findByUsername(username);
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
