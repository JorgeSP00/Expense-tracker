package com.example.expense_tracker.repository;

import com.example.expense_tracker.model.Expense;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository interface for managing {@link Expense} entities.
 * Provides CRUD operations and custom queries for expenses in the database.
 */
public interface ExpenseRepository extends JpaRepository<Expense, Long> {


    @Query("SELECT e FROM Expense e WHERE e.expenseSheet.id = :expenseSheetId")
    List<Expense> findByExpenseSheetId(@Param("expenseSheetId") Long expenseSheetId);

    @Query("SELECT e FROM Expense e WHERE e.category.id = :categoryId")
    List<Expense> findByCategoryId(@Param("categoryId") Long categoryId);
    
}