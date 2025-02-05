package com.example.auth_service.controller;


import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth_service.model.Users;
import com.example.auth_service.repository.ResetPasswordToken;
import com.example.auth_service.repository.UserRepo;
import com.example.auth_service.service.UserService;

@RestController
public class UserController {
    

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private ResetPasswordToken resetPasswordToken;


    @GetMapping("/auth/get-user-info")
    public Users getUserByUsername(@RequestBody String username){
        return userService.getUserByUsername(username);
    }
    

    // @PostMapping("/login")
    // public UserPrinciple login(@RequestBody String username){
    //     return userService.getUserForlogin(username);
    // }

    @GetMapping("/users")
    public List<Users> getAllUsers(){
        System.out.println("Request is coming here in other controller");
        return userRepo.findAll();
    }

    @Scheduled(cron = "0 0 3 * * ?")
    public void deleteExpiredTokens(){
        resetPasswordToken.deleteByExpirationTimeBefore(Instant.now());
    }



}
