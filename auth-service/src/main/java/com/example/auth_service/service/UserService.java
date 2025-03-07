package com.example.auth_service.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.auth_service.model.LoginDTO;
import com.example.auth_service.model.UserResponse;
import com.example.auth_service.model.Users;
import com.example.auth_service.repository.UserRepo;

@Service
public class UserService {
    
    @Autowired
    UserRepo userRepo;

    @Autowired
    private ResourceLoader resourceLoader;

    

    @Value("${app.profile.upload.dir}")
    private String uploadDir;

        private static final Set<String> ALLOWED_IMAGE_MIME_TYPES = new HashSet<>(Arrays.asList(
            "image/jpeg",
            "image/png",
            "image/gif",
            "image/webp",
            "image/bmp" // Add more image MIME types if needed
    ));

    private final String DEFAULT_PROFILE_IMAGE_PATH = "classpath:static/uploads/default.png";

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
            userResponse.setProfileImageUrl(user.getProfileImageUrl());

            return userResponse;
        }

        return null;
    }

    public Users getUserByUsername(String username) {
        Users user = userRepo.findByUsername(username);

        
        return user;
    }


    
    public Users addUser(Users user) {
        user.setCreated_at(LocalDateTime.now());
        try {
            String defaultProfileImagePath = copyDefaultProfileImage();
            user.setProfileImageUrl(defaultProfileImagePath);
        } catch (IOException e) {
            // Handle exception, maybe log it and set a default placeholder URL in DB
            System.err.println("Error setting default profile image: " + e.getMessage());
            user.setProfileImageUrl("default/placeholder/image/url"); // Or some default URL if copy fails
        }
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

    public Users updateUser(Users user){
        Optional<Users> existingUserOptional = userRepo.findById(user.getUser_id());
        if(existingUserOptional.isPresent()){
            Users existingUser = existingUserOptional.get();
            // Allow user to update these fields
            existingUser.setUsername(user.getUsername());
            existingUser.setFirst_name(user.getFirst_name());
            existingUser.setLast_name(user.getLast_name());
            existingUser.setEmail(user.getEmail());
            existingUser.setProfileImageUrl(user.getProfileImageUrl());
            return userRepo.save(existingUser);
        }
        return null; // Or throw exception if user not found
    }


     public Users uploadProfileImage(Long userId, MultipartFile file) throws IOException {
        Optional<Users> userOptional = userRepo.findById(userId);
        // Path uploadPath = Paths.get(uploadDir);
        
        if (!userOptional.isPresent()) {
            return null; // Or throw UserNotFoundException
        }
        Users user = userOptional.get();

        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // Generate a unique filename (to avoid collisions)
        String fileExtension = "";
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) {
            fileExtension = fileName.substring(dotIndex);
        }
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

        // Create the directory if it doesn't exist
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Path to save the file
        Path filePath = uploadPath.resolve(uniqueFileName);

        // Copy the file to the specified location (using try-with-resources to ensure stream closure)
        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Could not save image file: " + uniqueFileName, e);
        }

        // Save the file path in the user's profile
        String profileImageUrl = filePath.toString(); // Or you might want to store a relative path
        user.setProfileImageUrl(profileImageUrl);
        return userRepo.save(user);
    }

    private String copyDefaultProfileImage() throws IOException {
        Resource defaultImageResource = resourceLoader.getResource(DEFAULT_PROFILE_IMAGE_PATH);
        if (!defaultImageResource.exists()) {
            throw new IOException("Default profile image not found: " + DEFAULT_PROFILE_IMAGE_PATH);
        }

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String defaultImageFilename = StringUtils.cleanPath(defaultImageResource.getFilename()); // Get filename from path
        String uniqueDefaultImageName = UUID.randomUUID().toString() + "_" + defaultImageFilename; // Make filename unique
        Path destinationPath = uploadPath.resolve(uniqueDefaultImageName);

        try {
            Files.copy(defaultImageResource.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Failed to copy default profile image: " + e.getMessage(), e);
        }
        return destinationPath.toString(); // Return the path to the copied default image
    }

    

   
}
