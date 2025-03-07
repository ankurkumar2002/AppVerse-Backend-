package com.example.subscription_service.Controller;



import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.subscription_service.Entity.Applications;
import com.example.subscription_service.Entity.Subscription;
import com.example.subscription_service.Service.SubscriptionService;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/{subscriptionId}")
    public ResponseEntity<?> getSubscriptionById(@PathVariable Long subscriptionId) {
        try {
            Subscription subscription = subscriptionService.getSubscriptionById(subscriptionId);
            return ResponseEntity.ok(subscription);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Subscription>> getAllSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getAllSubscriptions());
    }

    @PostMapping("/users/{userId}/applications/{applicationId}")
    public ResponseEntity<?> subscribeUserToApplication(
            @PathVariable Long userId,
            @PathVariable Long applicationId) {
        try {
            Subscription subscription = subscriptionService.subscribeUserToApplication(userId, applicationId);
            return new ResponseEntity<>(subscription, HttpStatus.CREATED);
        } catch (EntityNotFoundException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // 400 for bad requests
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to subscribe user: " + e.getMessage()); // 500 for internal server error
        }
    }

    @DeleteMapping("/{subscriptionId}")
    public ResponseEntity<?> unsubscribeUser(@PathVariable Long subscriptionId) {
        try {
            subscriptionService.unsubscribeUserFromApplication(subscriptionId);
            return ResponseEntity.ok("User unsubscribed successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // 404 if subscription not found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to unsubscribe user: " + e.getMessage()); // 500 for other errors
        }
    }


    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Applications>> getUserSubscribedApplications(@PathVariable Long userId) {
        return ResponseEntity.ok(subscriptionService.getUserSubscribedApplications(userId));
    }

    @GetMapping("/users/{userId}/count")
    public ResponseEntity<Long> getSubscribedAppsCount(@PathVariable Long userId) {
        return ResponseEntity.ok(subscriptionService.countUserSubscribedApplications(userId));
    }

     @GetMapping("/applications/{applicationId}/users")
    public ResponseEntity<List<Long>> getApplicationUsers(@PathVariable Long applicationId) {
        return ResponseEntity.ok(subscriptionService.getApplicationSubscribersUserIds(applicationId));
    }
}