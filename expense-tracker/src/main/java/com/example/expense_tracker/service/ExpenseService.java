package com.example.expense_tracker.service;

import com.example.expense_tracker.model.Expense;
import com.example.expense_tracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    /**
     * @swagger Retrieve all expenses
     * @operationId getAllExpenses
     * @tags Expense
     * @response 200 List of Expense objects
     */
    @Operation(summary = "Retrieve all expenses")
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    /**
     * @swagger Retrieve an expense by its ID
     * @operationId getExpenseById
     * @tags Expense
     * @parameter id path required - Expense ID
     * @response 200 Expense object
     * @response 404 Not found
     */
    @Operation(summary = "Retrieve an expense by its ID")
    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    /**
     * @swagger Create a new expense
     * @operationId createExpense
     * @tags Expense
     * @requestBody Expense object
     * @response 201 Created Expense object
     */
    @Operation(summary = "Create a new expense")
    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    /**
     * @swagger Update an existing expense by ID
     * @operationId updateExpense
     * @tags Expense
     * @parameter id path required - Expense ID
     * @requestBody Updated Expense object
     * @response 200 Updated Expense object
     * @response 404 Not found
     */
    @Operation(summary = "Update an existing expense by ID")
    public Optional<Expense> updateExpense(Long id, Expense updatedExpense) {
        return expenseRepository.findById(id)
                .map(existingExpense -> {
                    updatedExpense.setId(id);
                    return expenseRepository.save(updatedExpense);
                });
    }

    /**
     * @swagger Delete an expense by ID
     * @operationId deleteExpense
     * @tags Expense
     * @parameter id path required - Expense ID
     * @response 204 No content
     */
    public Optional<Object> deleteExpense(Long id) {
        return expenseRepository.findById(id)
                .map(expense -> {
                    expenseRepository.delete(expense);
                    return new Object(); // Representa éxito sin contenido
                });
    }


    /**
     * @swagger Retrieve expensea by its Category ID
     * @operationId getExpensesByCategory
     * @tags Expense
     * @parameter id path required - Category ID
     * @response 200 Expense object
     * @response 404 Not found
     */
    public List<Expense> getExpensesByCategory(Long categoryId) {
        return expenseRepository.findByCategoryId(categoryId);
    }

    /**
     * @swagger Retrieve expensea by its Expense sheet ID
     * @operationId getExpensesByExpenseSheet
     * @tags Expense
     * @parameter id path required - Expense sheet ID
     * @response 200 Expense object
     * @response 404 Not found
     */
    public List<Expense> getExpensesByExpenseSheet(Long expenseSheetId) {
        return expenseRepository.findByExpenseSheetId(expenseSheetId);
    }
}