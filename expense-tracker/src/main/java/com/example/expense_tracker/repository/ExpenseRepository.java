package com.example.expense_tracker.repository;

import com.example.expense_tracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Expense} entities.
 * Provides CRUD operations and custom queries for expenses in the database.
 */
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}