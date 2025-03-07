package com.example.subscription_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.subscription_service.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByCategoryName(String categoryName);
    
}
