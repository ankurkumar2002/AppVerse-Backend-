package com.example.auth_service.controller;


import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.auth_service.exceptions.CustomExceptions.UserNotFoundException;
import com.example.auth_service.model.UserResponse;
import com.example.auth_service.model.Users;
import com.example.auth_service.repository.ResetPasswordToken;
import com.example.auth_service.repository.UserRepo;
import com.example.auth_service.service.JwtService;
import com.example.auth_service.service.MyUserDetailsService;
import com.example.auth_service.service.UserService;
import com.netflix.discovery.converters.Auto;

@RestController
public class UserController {
    

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private ResetPasswordToken resetPasswordToken;


    @GetMapping("/auth/get-user-info")
    public Users getUserByUsername(@RequestParam("username") String username ){
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


    @PutMapping("/auth/edit") // Endpoint for updating user details
    public ResponseEntity<?> updateUserDetails(@RequestBody Users user) {
        try {
            Users updatedUser = userService.updateUser(user); // Call UserService - now returns User entity
            // **Correct way to load UserDetails:**
            UserDetails updatedUserDetails = myUserDetailsService.loadUserByUsername(updatedUser.getUsername()); // Load UserDetails using UserDetailsService
            String newToken = jwtService.generateToken(updatedUserDetails, false); // Generate new token - **Corrected to use UserDetails**

            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + newToken); // Set Authorization header with new token

            return ResponseEntity.ok().headers(headers).body(updatedUser); // Return 200 OK with headers and updated user
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update user details: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    

    @PostMapping(value = "auth/profile-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadProfileImage(
            @RequestParam("userId") Long userId,
            @RequestParam("image") MultipartFile image) {
        try {
            Users updatedUser = userService.uploadProfileImage(userId, image);
            if (updatedUser != null) {
                return new ResponseEntity<>(updatedUser, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            if (e.getMessage().equals("Invalid file type. Only image files are allowed.")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE); // 415 for invalid file type
            } else {
                return new ResponseEntity<>("Could not upload image: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500 for other IO errors
            }
        }
    }


    @GetMapping("/auth/profile-image")
    public ResponseEntity<InputStreamResource> getProfileImage(@RequestParam("imagePath") String imagePath) throws IOException {
        System.out.println("Received imagePath parameter: " + imagePath); // Log the parameter
        Path imageFile = Paths.get(imagePath);
        System.out.println("Constructed imageFile path: " + imageFile.toString()); // Log the constructed path
        if (!Files.exists(imageFile) || Files.isDirectory(imageFile)) {
            System.out.println("Image file NOT FOUND at path: " + imageFile.toString()); // Log when not found

            return ResponseEntity.notFound().build();
        }

        String filename = imageFile.getFileName().toString();
        String mimeType = URLConnection.guessContentTypeFromName(filename);
        MediaType mediaType = null;

        if (mimeType != null && mimeType.startsWith("image/")) {
            mediaType = MediaType.parseMediaType(mimeType);
        } else {
            // Default to fallback type if MIME type detection fails or is not an image
            mediaType = MediaType.APPLICATION_OCTET_STREAM; // Or MediaType.IMAGE_JPEG as a safer fallback for image-like content
        }


        InputStreamResource resource = new InputStreamResource(Files.newInputStream(imageFile));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }



}
