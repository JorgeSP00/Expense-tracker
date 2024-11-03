package com.example.expense_tracker.repository;

import com.example.expense_tracker.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Goal} entities.
 * Provides CRUD operations and custom queries for goals in the database.
 */
public interface GoalRepository extends JpaRepository<Goal, Long> {
}