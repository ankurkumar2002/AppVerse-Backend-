package com.example.subscription_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.subscription_service.Entity.Applications;

public interface ApplicationRepository extends JpaRepository<Applications, Long> {

    Applications findByApplicationName(String applicationName);
    
}
