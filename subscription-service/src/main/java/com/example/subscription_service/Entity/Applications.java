package com.example.subscription_service.Entity;


import jakarta.persistence.*;
import java.math.BigDecimal; // For pricing
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "applications") // Explicitly name the table, good practice
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="applicationId")
public class Applications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @Column(nullable = false, unique = true, length = 100) // Add constraints for database
    private String applicationName;

    @Column(length = 500) // Limit description length
    private String description;

    @Column(nullable = false, length = 255) // Add constraints
    private String applicationUrl;

    @Column(length = 255)
    private String applicationIconUrl;

    @Column(length = 255)
    private String vendor; // Application vendor/developer name

    @Column(columnDefinition = "TEXT") // Use TEXT for potentially long feature lists
    private String features; // Comma-separated list or JSON array of features

    @Column(precision = 10, scale = 2) // Use BigDecimal for currency
    private BigDecimal price; // Price of the application (if applicable)

    @ManyToOne
    @JoinColumn(name = "category_id") // Foreign key column name in applications table
    private Category category; // Application category (you might want to use a separate Category entity later)

    @Column(columnDefinition = "TEXT") // Use TEXT for potentially long screenshots URLs
    private String screenshots; // Comma-separated list or JSON array of screenshot URLs

    // Add timestamps for creation and update
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    // Relationships (Subscription remains)
    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Subscription> subscriptions = new HashSet<>();

    public Applications() {
    }

    public Applications(String applicationName, String description, String applicationUrl, String applicationIconUrl, String vendor, String features, BigDecimal price, Category category, String screenshots) {
        this.applicationName = applicationName;
        this.description = description;
        this.applicationUrl = applicationUrl;
        this.applicationIconUrl = applicationIconUrl;
        this.vendor = vendor;
        this.features = features;
        this.price = price;
        this.category = category;
        this.screenshots = screenshots;
    }

    // Getters and Setters (for all fields) ...

    // ... (Getters and Setters for all fields, including new ones) ...

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApplicationUrl() {
        return applicationUrl;
    }

    public void setApplicationUrl(String applicationUrl) {
        this.applicationUrl = applicationUrl;
    }

    public String getApplicationIconUrl() {
        return applicationIconUrl;
    }

    public void setApplicationIconUrl(String applicationIconUrl) {
        this.applicationIconUrl = applicationIconUrl;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(String screenshots) {
        this.screenshots = screenshots;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }
}