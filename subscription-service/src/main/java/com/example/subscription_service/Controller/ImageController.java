package com.example.subscription_service.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/images")
public class ImageController {

        private static final Logger logger = LoggerFactory.getLogger(ImageController.class); // Logger instance


    private static final String ICON_DIRECTORY = "C:/Users/10743084/OneDrive - LTIMindtree/Documents/practice/Task Manager/Backend/subscription-service/src/main/resources/static/images/icons"; // Adjust
    private static final String SCREENSHOT_DIRECTORY = "static/uploads/screenshots"; // Adjust

    @GetMapping(value = "/icons/{imageName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<?> getIcon(@PathVariable String imageName) throws IOException {
        logger.debug("Request to get icon with imageName: {}", imageName); // Log imageName

        return getImage(imageName, ICON_DIRECTORY);
    }

    @GetMapping(value = "/screenshots/{imageName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<?> getScreenshot(@PathVariable String imageName) throws IOException {
        logger.debug("Request to get screenshot with imageName: {}", imageName); // Log imageName

        return getImage(imageName, SCREENSHOT_DIRECTORY);
    }

    private ResponseEntity<?> getImage(String imageName, String directory) throws IOException {
        logger.debug("Attempting to get image from directory: {}, imageName: {}", directory, imageName); // Log directory and imageName

        Path imagePath = Paths.get(directory, imageName);
        logger.debug("Constructed imagePath: {}", imagePath.toString()); // Log constructed path

        if (!Files.exists(imagePath)) {
            logger.warn("Image file NOT FOUND at path: {}", imagePath.toString()); // Log if file not found

            return ResponseEntity.notFound().build();
        }

        logger.debug("Image file FOUND at path: {}", imagePath.toString()); // Log if file is found


        FileSystemResource resource = new FileSystemResource(imagePath.toFile());

        // Determine the content type based on the file extension (more robust)
        String contentType = Files.probeContentType(imagePath);
        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE; // Fallback
        }

        logger.debug("Content Type determined: {}", contentType); // Log content type


        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}