package com.example.expense_tracker.controller;

import com.example.expense_tracker.model.Expense;
import com.example.expense_tracker.service.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllExpenses_WhenExpensesExist_ShouldReturnOk() {
        List<Expense> expenses = Arrays.asList(new Expense(), new Expense());
        when(expenseService.getAllExpenses()).thenReturn(expenses);

        ResponseEntity<List<Expense>> response = expenseController.getAllExpenses();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(expenseService, times(1)).getAllExpenses();
    }

    @Test
    public void testGetAllExpenses_WhenNoExpensesExist_ShouldReturnNoContent() {
        when(expenseService.getAllExpenses()).thenReturn(List.of());

        ResponseEntity<List<Expense>> response = expenseController.getAllExpenses();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(expenseService, times(1)).getAllExpenses();
    }

    @Test
    public void testGetExpenseById_WhenExpenseExists_ShouldReturnOk() {
        Expense expense = new Expense();
        when(expenseService.getExpenseById(1L)).thenReturn(Optional.of(expense));

        ResponseEntity<Expense> response = expenseController.getExpenseById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expense, response.getBody());
    }

    @Test
    public void testGetExpenseById_WhenExpenseDoesNotExist_ShouldReturnNotFound() {
        when(expenseService.getExpenseById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Expense> response = expenseController.getExpenseById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(expenseService, times(1)).getExpenseById(1L);
    }

    @Test
    public void testCreateExpense_WhenValidExpense_ShouldReturnOk() {
        Expense expense = new Expense();
        when(expenseService.createExpense(expense)).thenReturn(expense);

        ResponseEntity<Expense> response = expenseController.createExpense(expense);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expense, response.getBody());
        verify(expenseService, times(1)).createExpense(expense);
    }

    @Test
    public void testCreateExpense_WhenExpenseIsNull_ShouldReturnBadRequest() {
        when(expenseService.createExpense(null)).thenReturn(null);

        ResponseEntity<Expense> response = expenseController.createExpense(null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(expenseService, times(1)).createExpense(null);
    }

    @Test
    public void testUpdateExpense_WhenExpenseExists_ShouldReturnOk() {
        Expense expense = new Expense();
        when(expenseService.updateExpense(1L, expense)).thenReturn(Optional.of(expense));

        ResponseEntity<Expense> response = expenseController.updateExpense(1L, expense);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expense, response.getBody());
    }

    @Test
    public void testUpdateExpense_WhenExpenseDoesNotExist_ShouldReturnNotFound() {
        Expense expense = new Expense();
        when(expenseService.updateExpense(1L, expense)).thenReturn(Optional.empty());

        ResponseEntity<Expense> response = expenseController.updateExpense(1L, expense);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(expenseService, times(1)).updateExpense(1L, expense);
    }

    @Test
    public void testDeleteExpense_WhenExpenseExists_ShouldReturnNoContent() {
        when(expenseService.deleteExpense(1L)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = expenseController.deleteExpense(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(expenseService, times(1)).deleteExpense(1L);
    }

    @Test
    public void testDeleteExpense_WhenExpenseDoesNotExist_ShouldReturnNotFound() {
        when(expenseService.deleteExpense(1L)).thenReturn(Optional.ofNullable(null));

        ResponseEntity<Object> response = expenseController.deleteExpense(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(expenseService, times(1)).deleteExpense(1L);
    }
}