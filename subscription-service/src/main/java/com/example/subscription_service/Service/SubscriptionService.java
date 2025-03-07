package com.example.subscription_service.Service;



import com.example.subscription_service.Entity.Applications;
import com.example.subscription_service.Entity.Subscription;
import com.example.subscription_service.Repository.ApplicationRepository;
import com.example.subscription_service.Repository.SubscriptionRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepo;
    @Autowired
    private ApplicationRepository applicationRepo; // Need ApplicationRepo for validation

    public Subscription getSubscriptionById(Long subscriptionId) {
        return subscriptionRepo.findById(subscriptionId)
                .orElseThrow(() -> new EntityNotFoundException("Subscription not found with id: " + subscriptionId));
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepo.findAll();
    }

    public Subscription subscribeUserToApplication(Long userId, Long applicationId) {
        Applications application = applicationRepo.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException("Application not found with id: " + applicationId));

        if (subscriptionRepo.findByUserIdAndApplication_ApplicationId(userId, applicationId) != null) {
            throw new IllegalStateException("User is already subscribed to this application");
        }

        Subscription subscription = new Subscription(userId, application);
        return subscriptionRepo.save(subscription);
    }

    public void unsubscribeUserFromApplication(Long subscriptionId) {
        Subscription subscription = getSubscriptionById(subscriptionId);
        subscriptionRepo.delete(subscription);
    }

    public List<Applications> getUserSubscribedApplications(Long userId) {
        List<Subscription> subscriptions = subscriptionRepo.findByUserId(userId);
        return subscriptions.stream()
                .map(Subscription::getApplication)
                .collect(Collectors.toList());
    }

    public long countUserSubscribedApplications(Long userId) {
        return subscriptionRepo.countByUserId(userId);
    }

    public List<Long> getApplicationSubscribersUserIds(Long applicationId) {
        List<Subscription> subscriptions = subscriptionRepo.findByApplication_ApplicationId(applicationId);
        return subscriptions.stream()
                .map(Subscription::getUserId)
                .collect(Collectors.toList());
    }
}