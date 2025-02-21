package com.example.expense_tracker.controller;

import com.example.expense_tracker.model.ExpenseSheet;
import com.example.expense_tracker.service.ExpenseSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expense-sheets")
@Tag(name = "ExpenseSheet", description = "API for managing expense sheets")
public class ExpenseSheetController {

    @Autowired
    private ExpenseSheetService expenseSheetService;

    /**
     * @swagger Get all expense sheets
     * @operationId getAllExpenseSheets
     * @tags ExpenseSheet
     * @response 200 List of expense sheets
     */
    @Operation(summary = "Get all expense sheets")
    @GetMapping
    public ResponseEntity<List<ExpenseSheet>> getAllExpenseSheets() {
        return Optional.ofNullable(expenseSheetService.getAllExpenseSheets())
                .filter(sheets -> !sheets.isEmpty())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    /**
     * @swagger Get an expense sheet by ID
     * @operationId getExpenseSheetById
     * @tags ExpenseSheet
     * @parameter id path required - ID of the expense sheet
     * @response 200 Expense sheet object
     * @response 404 Not found
     */
    @Operation(summary = "Get an expense sheet by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseSheet> getExpenseSheetById(@Parameter(description = "ID of the expense sheet") @PathVariable Long id) {
        return expenseSheetService.getExpenseSheetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * @swagger Create a new expense sheet
     * @operationId createExpenseSheet
     * @tags ExpenseSheet
     * @requestBody Expense sheet object to create
     * @response 201 Created expense sheet object
     */
    @Operation(summary = "Create a new expense sheet")
    @PostMapping
    public ResponseEntity<ExpenseSheet> createExpenseSheet(@RequestBody ExpenseSheet sheet) {
        return Optional.ofNullable(expenseSheetService.createExpenseSheet(sheet))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    /**
     * @swagger Update an existing expense sheet by ID
     * @operationId updateExpenseSheet
     * @tags ExpenseSheet
     * @parameter id path required - ID of the expense sheet to update
     * @requestBody Updated expense sheet object
     * @response 200 Updated expense sheet object
     * @response 404 Not found
     */
    @Operation(summary = "Update an existing expense sheet")
    @PutMapping("/{id}")
    public ResponseEntity<ExpenseSheet> updateExpenseSheet(@PathVariable Long id, @RequestBody ExpenseSheet expenseSheet) {
        return expenseSheetService.updateExpenseSheet(id, expenseSheet)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * @swagger Delete an expense sheet by ID
     * @operationId deleteExpenseSheet
     * @tags ExpenseSheet
     * @parameter id path required - ID of the expense sheet to delete
     * @response 204 No content
     */
    @Operation(summary = "Delete an expense sheet by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteExpenseSheet(@PathVariable Long id) {
        return expenseSheetService.deleteExpenseSheet(id)
                .map(deleted -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }
}