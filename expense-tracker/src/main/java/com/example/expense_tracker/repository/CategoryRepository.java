package com.example.expense_tracker.repository;

import com.example.expense_tracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Category} entities.
 * Provides CRUD operations and custom queries for categories in the database.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
