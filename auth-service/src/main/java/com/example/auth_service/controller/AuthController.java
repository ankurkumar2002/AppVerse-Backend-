package com.example.auth_service.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth_service.exceptions.CustomExceptions.UserAlreadyExistsException;
import com.example.auth_service.model.ForgotPasswordDTO;
import com.example.auth_service.model.ForgotPasswordEmail;
import com.example.auth_service.model.LoginDTO;
import com.example.auth_service.model.ResetPasswordRequest;
import com.example.auth_service.model.UserPrinciple;
import com.example.auth_service.model.UserResponse;
import com.example.auth_service.model.Users;
import com.example.auth_service.repository.ResetPasswordToken;
import com.example.auth_service.repository.UserRepo;
import com.example.auth_service.service.EmailService;
import com.example.auth_service.service.JwtService;
import com.example.auth_service.service.TokenGeneratorService;
import com.example.auth_service.service.UserService;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TokenGeneratorService tokenGeneratorService;

    @Autowired
    private ResetPasswordToken resetPasswordToken;

    @Autowired
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/signup")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> registerUser(@Validated @RequestBody Users user){

        if (userService.getUserByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException("User already exists!");
        }
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreated_at(LocalDateTime.now());
        return new ResponseEntity<>(userService.addUser(user),HttpStatus.OK);
    }

   

    @PostMapping("/login")
public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO) {
    Users user = userService.getUserByUsername(loginDTO.getUsername());
    if (user != null && passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
        UserDetails userDetails = new UserPrinciple(user); // Create UserDetails object
        UserResponse userResponse = userService.getUserInfoToAuthenticate(loginDTO.getUsername());
        String token = jwtService.generateToken(userDetails, loginDTO.isRememberMe()); // Use the updated method

        Map<String, Object> response =  new HashMap<>();
        response.put("jwtToken", token);
        response.put("userDetails", userResponse);

        return ResponseEntity.ok(response);
    } else {
        return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
    }
}


    @GetMapping("/users")
    public List<Users> getAllUsers(){
        System.out.println("Request is coming here");
        return userService.getAllUsers();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(){
        return new ResponseEntity<>("Logged Out",HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordEmail email){

        Users user = userService.findByEmail(email.getEmail()).get();
        
        

        String rawToken = tokenGeneratorService.generateToken();
        ForgotPasswordDTO resetToken = new ForgotPasswordDTO();

        resetToken.setToken(rawToken);
        resetToken.setUser(user);
        resetToken.setExpirationTime(Instant.now().plus(1 , ChronoUnit.HOURS));
        
        resetPasswordToken.save(resetToken);

        String resetLink = "https://localhost:8060/reset-password?token="+rawToken;
        System.out.println(resetLink);
        emailService.sendEmail(email.getEmail(), "Password Reset Link", "Click here to Reset your password: "+resetLink);
        return ResponseEntity.ok("Reset Link sent Successfully");

        // return null;
    }


    @PostMapping("/reset-password")
    public ResponseEntity<?>  resetPassword(@RequestBody ResetPasswordRequest request){
        ForgotPasswordDTO resetToken = resetPasswordToken.findAll().stream()
                                                                    .filter(t -> t.isValid(request.getToken()))
                                                                    .findFirst()
                                                                    .orElseThrow(() -> new RuntimeException("Invalid Token"));


        if (resetToken.getExpirationTime().isBefore(Instant.now())) {
            throw new RuntimeException("TOken Expired");
        }                         
        
        Users user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepo.save(user);

        resetPasswordToken.delete(resetToken);
        return ResponseEntity.ok("Password Reset Successfull");
    }



    





}
