package com.example.subscription_service.Service;


import com.example.subscription_service.Entity.Applications;
import com.example.subscription_service.Entity.Category;
import com.example.subscription_service.Repository.ApplicationRepository;
import com.example.subscription_service.Repository.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepo;
    @Autowired
    private CategoryRepository categoryRepo; 

    public Applications getApplicationById(Long applicationId) {
        return applicationRepo.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException("Application not found with id: " + applicationId));
    }

    public List<Applications> getAllApplications() {
        return applicationRepo.findAll();
    }

    public Applications createApplication(Applications application, Long categoryId) {
        Category existingCategory = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + categoryId));
        application.setCategory(existingCategory);
    
    
        // Check if application with the same name already exists
        Applications existingApplication = applicationRepo.findByApplicationName(application.getApplicationName());
    
        if (existingApplication != null) {
          throw new IllegalStateException("An application with the name '" + application.getApplicationName() + "' already exists.");
        } else {
    
        return applicationRepo.save(application);
    
        }
    
    }

    public Applications updateApplication(Long applicationId, Applications updatedApplication, Long categoryId) {
        Applications existingApplication = getApplicationById(applicationId);
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + categoryId));

        existingApplication.setApplicationName(updatedApplication.getApplicationName());
        existingApplication.setApplicationUrl(updatedApplication.getApplicationUrl());
        existingApplication.setApplicationIconUrl(updatedApplication.getApplicationIconUrl());
        existingApplication.setDescription(updatedApplication.getDescription());
        existingApplication.setVendor(updatedApplication.getVendor());
        existingApplication.setFeatures(updatedApplication.getFeatures());
        existingApplication.setPrice(updatedApplication.getPrice());
        existingApplication.setScreenshots(updatedApplication.getScreenshots());
        existingApplication.setCategory(category); 

        return applicationRepo.save(existingApplication);
    }

    public void deleteApplication(Long applicationId) {
        Applications application = getApplicationById(applicationId);
        applicationRepo.delete(application);
    }
}