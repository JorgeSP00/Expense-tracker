package com.example.expense_tracker.repository;

import com.example.expense_tracker.model.ExpenseSheet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link ExpenseSheet} entities.
 * Provides CRUD operations and custom queries for expense sheets in the database.
 */
public interface ExpenseSheetRepository extends JpaRepository<ExpenseSheet, Long> {
}
