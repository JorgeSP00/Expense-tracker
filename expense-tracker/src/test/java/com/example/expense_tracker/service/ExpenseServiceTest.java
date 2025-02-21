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

import static org.junit.jupiter.api.Assertions.*;
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
    public void testGetAllExpenses_EmptyList() {
        when(expenseRepository.findAll()).thenReturn(List.of());

        List<Expense> result = expenseService.getAllExpenses();

        assertTrue(result.isEmpty());
        verify(expenseRepository, times(1)).findAll();
    }

    @Test
    public void testGetExpenseById() {
        Expense expense = new Expense();
        when(expenseRepository.findById(1L)).thenReturn(Optional.of(expense));

        Optional<Expense> result = expenseService.getExpenseById(1L);

        assertTrue(result.isPresent());
        assertEquals(expense, result.get());
        verify(expenseRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetExpenseById_NotFound() {
        when(expenseRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Expense> result = expenseService.getExpenseById(1L);

        assertFalse(result.isPresent());
        verify(expenseRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateExpense() {
        Expense expense = new Expense();
        when(expenseRepository.save(expense)).thenReturn(expense);

        Expense result = expenseService.createExpense(expense);

        assertNotNull(result);
        assertEquals(expense, result);
        verify(expenseRepository, times(1)).save(expense);
    }

    @Test
    public void testUpdateExpense() {
        Expense existingExpense = new Expense();
        Expense updatedExpense = new Expense();
        when(expenseRepository.findById(1L)).thenReturn(Optional.of(existingExpense));
        when(expenseRepository.save(updatedExpense)).thenReturn(updatedExpense);

        Optional<Expense> result = expenseService.updateExpense(1L, updatedExpense);

        assertTrue(result.isPresent());
        assertEquals(updatedExpense, result.get());
        verify(expenseRepository, times(1)).save(updatedExpense);
    }

    @Test
    public void testUpdateExpense_NotFound() {
        Expense updatedExpense = new Expense();
        when(expenseRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Expense> result = expenseService.updateExpense(1L, updatedExpense);

        assertFalse(result.isPresent());
        verify(expenseRepository, times(0)).save(any());
    }

    @Test
    public void testDeleteExpense() {
        Expense expense = new Expense();
        when(expenseRepository.findById(1L)).thenReturn(Optional.of(expense));
        doNothing().when(expenseRepository).delete(expense);

        Optional<Object> result = expenseService.deleteExpense(1L);

        assertTrue(result.isPresent());
        verify(expenseRepository, times(1)).delete(expense);
    }

    @Test
    public void testDeleteExpense_NotFound() {
        when(expenseRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Object> result = expenseService.deleteExpense(1L);

        assertFalse(result.isPresent());
        verify(expenseRepository, times(0)).delete(any());
    }

    @Test
    public void testGetExpensesByCategory() {
        Long categoryId = 1L;
        List<Expense> expenses = Arrays.asList(new Expense(), new Expense());

        when(expenseRepository.findByCategoryId(categoryId)).thenReturn(expenses);

        List<Expense> result = expenseService.getExpensesByCategory(categoryId);

        assertEquals(2, result.size());
        verify(expenseRepository, times(1)).findByCategoryId(categoryId);
    }

    @Test
    public void testGetExpensesByCategory_EmptyList() {
        Long categoryId = 1L;
        when(expenseRepository.findByCategoryId(categoryId)).thenReturn(List.of());

        List<Expense> result = expenseService.getExpensesByCategory(categoryId);

        assertTrue(result.isEmpty());
        verify(expenseRepository, times(1)).findByCategoryId(categoryId);
    }

    @Test
    public void testGetExpensesByExpenseSheet() {
        Long expenseSheetId = 1L;
        List<Expense> expenses = Arrays.asList(new Expense(), new Expense());

        when(expenseRepository.findByExpenseSheetId(expenseSheetId)).thenReturn(expenses);

        List<Expense> result = expenseService.getExpensesByExpenseSheet(expenseSheetId);

        assertEquals(2, result.size());
        verify(expenseRepository, times(1)).findByExpenseSheetId(expenseSheetId);
    }

    @Test
    public void testGetExpensesByExpenseSheet_EmptyList() {
        Long expenseSheetId = 1L;
        when(expenseRepository.findByExpenseSheetId(expenseSheetId)).thenReturn(List.of());

        List<Expense> result = expenseService.getExpensesByExpenseSheet(expenseSheetId);

        assertTrue(result.isEmpty());
        verify(expenseRepository, times(1)).findByExpenseSheetId(expenseSheetId);
    }
}