package com.example.subscription_service.Controller;



import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import com.example.subscription_service.Entity.Applications;
import com.example.subscription_service.Service.ApplicationService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/{applicationId}")
    public ResponseEntity<?> getApplicationById(@PathVariable Long applicationId) {
        try {
            Applications application = applicationService.getApplicationById(applicationId);
            return ResponseEntity.ok(application);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Applications>> getAllApplications() {
        return ResponseEntity.ok(applicationService.getAllApplications());
    }

    @PostMapping
    public ResponseEntity<?> createApplication(@RequestBody Applications application, @RequestParam Long categoryId) {
        try {
            CompletableFuture<Applications> future = createApplicationAsync(application, categoryId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Application  creation initiated. Check back later.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create application: " + e.getMessage());
        }
    }

    @Async("taskExecutor")
    public CompletableFuture<Applications> createApplicationAsync(Applications application, Long categoryId){
        try {
            Applications createdApplication = applicationService.createApplication(application, categoryId);
            return CompletableFuture.completedFuture(createdApplication);
        } catch (Exception e) {
            System.err.println("Async create application failed: "+ e.getMessage());
            return CompletableFuture.failedFuture(e);
        }
    }

    @PutMapping("/{applicationId}")
    public ResponseEntity<?> updateApplication(@PathVariable Long applicationId, @RequestBody Applications updatedApplication, @RequestParam Long categoryId) {
        try {
            updateApplicationAsync(applicationId, updatedApplication, categoryId);
            return ResponseEntity.ok("Application udpate initiated. Please check back later.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update application: " + e.getMessage());
        }
    }

    @Async 
    public void updateApplicationAsync(Long applicationId, Applications updatedApplications, Long  categoryId){
        try {
            applicationService.updateApplication(applicationId, updatedApplications, categoryId);
        } catch (Exception e) {
            System.err.println("Async update application failed: "+ e.getMessage());
        }
    }

    @DeleteMapping("/{applicationId}")
    public ResponseEntity<?> deleteApplication(@PathVariable Long applicationId) {
        try {
            deleteApplicationAsync(applicationId);
            return ResponseEntity.ok("Application deleted successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete application: " + e.getMessage());
        }
    }

    @Async
    public void deleteApplicationAsync(Long applicationId){
        try {
            applicationService.deleteApplication(applicationId);
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("Async delete application failed: "+ e.getMessage());
        }
    }
}