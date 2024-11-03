package com.example.expense_tracker.service;

import com.example.expense_tracker.model.Expense;
import com.example.expense_tracker.repository.ExpenseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private ExpenseService expenseService;

    public ExpenseServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllExpenses() {
        List<Expense> expenses = Arrays.asList(new Expense(), new Expense());
        when(expenseRepository.findAll()).thenReturn(expenses);

        List<Expense> result = expenseService.getAllExpenses();

        assertEquals(2, result.size());
        verify(expenseRepository, times(1)).findAll();
    }

    @Test
    public void testGetExpenseById() {
        Expense expense = new Expense();
        when(expenseRepository.findById(1L)).thenReturn(Optional.of(expense));

        Optional<Expense> result = expenseService.getExpenseById(1L);

        assertEquals(Optional.of(expense), result);
        verify(expenseRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateExpense() {
        Expense expense = new Expense();
        when(expenseRepository.save(expense)).thenReturn(expense);

        Expense result = expenseService.createExpense(expense);

        assertEquals(expense, result);
        verify(expenseRepository, times(1)).save(expense);
    }

    @Test
    public void testUpdateExpense() {
        Expense expense = new Expense();
        when(expenseRepository.existsById(1L)).thenReturn(true);
        when(expenseRepository.save(expense)).thenReturn(expense);

        Expense result = expenseService.updateExpense(1L, expense);

        assertEquals(expense, result);
        verify(expenseRepository, times(1)).save(expense);
    }

    @Test
    public void testDeleteExpense() {
        doNothing().when(expenseRepository).deleteById(1L);

        expenseService.deleteExpense(1L);

        verify(expenseRepository, times(1)).deleteById(1L);
    }
}