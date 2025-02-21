package com.example.expense_tracker.service;

import com.example.expense_tracker.model.Category;
import com.example.expense_tracker.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * @swagger Retrieve all categories
     * @operationId getAllCategories
     * @tags Category
     * @response 200 List of Category objects
     */
    @Operation(summary = "Retrieve all categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * @swagger Retrieve a category by its ID
     * @operationId getCategoryById
     * @tags Category
     * @parameter id path required - Category ID
     * @response 200 Category object
     * @response 404 Not found
     */
    @Operation(summary = "Retrieve a category by its ID")
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    /**
     * @swagger Create a new category
     * @operationId createCategory
     * @tags Category
     * @requestBody Category object
     * @response 201 Created Category object
     */
    @Operation(summary = "Create a new category")
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * @swagger Update an existing category by ID
     * @operationId updateCategory
     * @tags Category
     * @parameter id path required - Category ID
     * @requestBody Updated Category object
     * @response 200 Updated Category object
     * @response 404 Not found
     */
    @Operation(summary = "Update an existing category by ID")
    public Optional<Category> updateCategory(Long id, Category updatedCategory) {
        return categoryRepository.findById(id)
                .map(existingCategory -> {
                    updatedCategory.setId(id);
                    return categoryRepository.save(updatedCategory);
                });
    }
    

    /**
     * @swagger Delete a category by ID
     * @operationId deleteCategory
     * @tags Category
     * @parameter id path required - Category ID
     * @response 204 No content
     */
    @Operation(summary = "Delete a category by ID")
    public Optional<Object> deleteCategory(Long id) {
        return categoryRepository.findById(id)
                .map(category -> {
                    categoryRepository.delete(category);
                    return new Object(); // Representa éxito sin contenido
                });
    }
    
}