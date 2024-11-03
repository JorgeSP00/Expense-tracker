package com.example.expense_tracker.controller;

import com.example.expense_tracker.model.Expense;
import com.example.expense_tracker.service.ExpenseService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class ExpenseControllerTest {

    @Mock
    private ExpenseService expenseService;

    @InjectMocks
    private ExpenseController expenseController;

    public ExpenseControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllExpenses() {
        List<Expense> expenses = Arrays.asList(new Expense(), new Expense());
        when(expenseService.getAllExpenses()).thenReturn(expenses);

        List<Expense> result = expenseController.getAllExpenses();

        assertEquals(2, result.size());
        verify(expenseService, times(1)).getAllExpenses();
    }

    @Test
    public void testGetExpenseById() {
        Expense expense = new Expense();
        when(expenseService.getExpenseById(1L)).thenReturn(Optional.of(expense));

        ResponseEntity<Expense> response = expenseController.getExpenseById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expense, response.getBody());
    }

    @Test
    public void testCreateExpense() {
        Expense expense = new Expense();
        when(expenseService.createExpense(expense)).thenReturn(expense);

        Expense result = expenseController.createExpense(expense);

        assertEquals(expense, result);
        verify(expenseService, times(1)).createExpense(expense);
    }

    @Test
    public void testUpdateExpense() {
        Expense expense = new Expense();
        when(expenseService.updateExpense(1L, expense)).thenReturn(expense);

        ResponseEntity<Expense> response = expenseController.updateExpense(1L, expense);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expense, response.getBody());
    }

    @Test
    public void testDeleteExpense() {
        doNothing().when(expenseService).deleteExpense(1L);

        ResponseEntity<Void> response = expenseController.deleteExpense(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(expenseService, times(1)).deleteExpense(1L);
    }
}