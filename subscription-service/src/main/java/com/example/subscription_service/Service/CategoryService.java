package com.example.subscription_service.Service;


import com.example.subscription_service.Entity.Category;
import com.example.subscription_service.Repository.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    public Category getCategoryById(Long categoryId) {
        return categoryRepo.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + categoryId));
    }

    public Category getCategoryByName(String categoryName) {
        Category category = categoryRepo.findByCategoryName(categoryName);
        if (category == null) {
            throw new EntityNotFoundException("Category not found with name: " + categoryName);
        }
        return category;
    }


    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Category createCategory(Category category) {
        // Add any business logic/validation before saving
        return categoryRepo.save(category);
    }

    public Category updateCategory(Long categoryId, Category updatedCategory) {
        Category existingCategory = getCategoryById(categoryId);
        existingCategory.setCategoryName(updatedCategory.getCategoryName());
        existingCategory.setCategoryDescription(updatedCategory.getCategoryDescription());
        return categoryRepo.save(existingCategory);
    }

    public void deleteCategory(Long categoryId) {
        Category category = getCategoryById(categoryId);
        // Add any business logic/validation before deleting (e.g., check if category is in use)
        categoryRepo.delete(category);
    }
}