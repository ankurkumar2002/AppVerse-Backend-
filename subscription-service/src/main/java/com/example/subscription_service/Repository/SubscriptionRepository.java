package com.example.subscription_service.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.subscription_service.Entity.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUserId(Long userId);
    Subscription findByUserIdAndApplication_ApplicationId(Long userId, Long applicationId);
    long countByUserId(Long userId);
    List<Subscription> findByApplication_ApplicationId(Long applicationId);
}
