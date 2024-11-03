package com.example.expense_tracker.controller;

import com.example.expense_tracker.model.Category;
import com.example.expense_tracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * @swagger Get all categories
     * @operationId getAllCategories
     * @tags Category
     * @response 200 List of categories
     */
    @Operation(summary = "Get all categories")
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    /**
     * @swagger Get a category by ID
     * @operationId getCategoryById
     * @tags Category
     * @parameter id path required - ID of the category
     * @response 200 Category object
     * @response 404 Not found
     */
    @Operation(summary = "Get a category by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * @swagger Create a new category
     * @operationId createCategory
     * @tags Category
     * @requestBody Category object to create
     * @response 201 Created category object
     */
    @Operation(summary = "Create a new category")
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    /**
     * @swagger Update an existing category by ID
     * @operationId updateCategory
     * @tags Category
     * @parameter id path required - ID of the category to update
     * @requestBody Updated category object
     * @response 200 Updated category object
     * @response 404 Not found
     */
    @Operation(summary = "Update an existing category by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(id, category);
        return updatedCategory != null ? ResponseEntity.ok(updatedCategory) : ResponseEntity.notFound().build();
    }

    /**
     * @swagger Delete a category by ID
     * @operationId deleteCategory
     * @tags Category
     * @parameter id path required - ID of the category to delete
     * @response 204 No content
     */
    @Operation(summary = "Delete a category by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}