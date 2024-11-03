package com.example.expense_tracker.controller;

import com.example.expense_tracker.model.Expense;
import com.example.expense_tracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@Tag(name = "Expense", description = "API for managing expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    /**
     * @swagger Get all expenses
     * @operationId getAllExpenses
     * @tags Expense
     * @response 200 List of expenses
     */
    @Operation(summary = "Get all expenses")
    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    /**
     * @swagger Get an expense by ID
     * @operationId getExpenseById
     * @tags Expense
     * @parameter id path required - ID of the expense
     * @response 200 Expense object
     * @response 404 Not found
     */
    @Operation(summary = "Get an expense by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * @swagger Create a new expense
     * @operationId createExpense
     * @tags Expense
     * @requestBody Expense object to create
     * @response 201 Created expense object
     */
    @Operation(summary = "Create a new expense")
    @PostMapping
    public Expense createExpense(@RequestBody Expense expense) {
        return expenseService.createExpense(expense);
    }

    /**
     * @swagger Update an existing expense by ID
     * @operationId updateExpense
     * @tags Expense
     * @parameter id path required - ID of the expense to update
     * @requestBody Updated expense object
     * @response 200 Updated expense object
     * @response 404 Not found
     */
    @Operation(summary = "Update an existing expense by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        Expense updatedExpense = expenseService.updateExpense(id, expense);
        return updatedExpense != null ? ResponseEntity.ok(updatedExpense) : ResponseEntity.notFound().build();
    }

    /**
     * @swagger Delete an expense by ID
     * @operationId deleteExpense
     * @tags Expense
     * @parameter id path required - ID of the expense to delete
     * @response 204 No content
     */
    @Operation(summary = "Delete an expense by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}