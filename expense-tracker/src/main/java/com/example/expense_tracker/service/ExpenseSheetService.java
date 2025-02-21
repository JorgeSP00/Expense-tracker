package com.example.expense_tracker.service;

import com.example.expense_tracker.model.ExpenseSheet;
import com.example.expense_tracker.repository.ExpenseSheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseSheetService {

    @Autowired
    private ExpenseSheetRepository expenseSheetRepository;

    /**
     * @swagger Retrieve all expense sheets
     * @operationId getAllExpenseSheets
     * @tags ExpenseSheet
     * @response 200 List of ExpenseSheet objects
     */
    public List<ExpenseSheet> getAllExpenseSheets() {
        return expenseSheetRepository.findAll();
    }

    /**
     * @swagger Retrieve an expense sheet by its ID
     * @operationId getExpenseSheetById
     * @tags ExpenseSheet
     * @parameter id path required - ExpenseSheet ID
     * @response 200 ExpenseSheet object
     * @response 404 Not found
     */
    public Optional<ExpenseSheet> getExpenseSheetById(Long id) {
        return expenseSheetRepository.findById(id);
    }

    /**
     * @swagger Create a new expense sheet
     * @operationId createExpenseSheet
     * @tags ExpenseSheet
     * @requestBody ExpenseSheet object
     * @response 201 Created ExpenseSheet object
     */
    public ExpenseSheet createExpenseSheet(ExpenseSheet sheet) {
        return expenseSheetRepository.save(sheet);
    }

    /**
     * @swagger Update an existing expense sheet by ID
     * @operationId updateExpenseSheet
     * @tags ExpenseSheet
     * @parameter id path required - ExpenseSheet ID
     * @requestBody Updated ExpenseSheet object
     * @response 200 Updated ExpenseSheet object
     * @response 404 Not found
     */
    public Optional<ExpenseSheet> updateExpenseSheet(Long id, ExpenseSheet updatedExpenseSheet) {
        return expenseSheetRepository.findById(id)
                .map(existingExpenseSheet -> {
                    updatedExpenseSheet.setId(id);
                    return expenseSheetRepository.save(updatedExpenseSheet);
                });
    }

    /**
     * @swagger Delete an expense sheet by ID
     * @operationId deleteExpenseSheet
     * @tags ExpenseSheet
     * @parameter id path required - ExpenseSheet ID
     * @response 204 No content
     */
    public Optional<Object> deleteExpenseSheet(Long id) {
        return expenseSheetRepository.findById(id)
                .map(expenseSheet -> {
                    expenseSheetRepository.delete(expenseSheet);
                    return new Object(); // Representa éxito sin contenido
                });
    }
}
