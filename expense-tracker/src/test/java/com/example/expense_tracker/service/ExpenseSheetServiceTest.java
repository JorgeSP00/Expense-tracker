package com.example.expense_tracker.service;

import com.example.expense_tracker.model.ExpenseSheet;
import com.example.expense_tracker.repository.ExpenseSheetRepository;
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
public class ExpenseSheetServiceTest {

    @Mock
    private ExpenseSheetRepository expenseSheetRepository;

    @InjectMocks
    private ExpenseSheetService expenseSheetService;

    public ExpenseSheetServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllExpenseSheets() {
        List<ExpenseSheet> sheets = Arrays.asList(new ExpenseSheet(), new ExpenseSheet());
        when(expenseSheetRepository.findAll()).thenReturn(sheets);

        List<ExpenseSheet> result = expenseSheetService.getAllExpenseSheets();

        assertEquals(2, result.size());
        verify(expenseSheetRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllExpenseSheets_EmptyList() {
        when(expenseSheetRepository.findAll()).thenReturn(List.of());

        List<ExpenseSheet> result = expenseSheetService.getAllExpenseSheets();

        assertTrue(result.isEmpty());
        verify(expenseSheetRepository, times(1)).findAll();
    }

    @Test
    public void testGetExpenseSheetById() {
        ExpenseSheet sheet = new ExpenseSheet();
        when(expenseSheetRepository.findById(1L)).thenReturn(Optional.of(sheet));

        Optional<ExpenseSheet> result = expenseSheetService.getExpenseSheetById(1L);

        assertTrue(result.isPresent());
        assertEquals(sheet, result.get());
        verify(expenseSheetRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetExpenseSheetById_NotFound() {
        when(expenseSheetRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<ExpenseSheet> result = expenseSheetService.getExpenseSheetById(1L);

        assertFalse(result.isPresent());
        verify(expenseSheetRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateExpenseSheet() {
        ExpenseSheet sheet = new ExpenseSheet();
        when(expenseSheetRepository.save(sheet)).thenReturn(sheet);

        ExpenseSheet result = expenseSheetService.createExpenseSheet(sheet);

        assertNotNull(result);
        assertEquals(sheet, result);
        verify(expenseSheetRepository, times(1)).save(sheet);
    }

    @Test
    public void testUpdateExpenseSheet() {
        ExpenseSheet existingSheet = new ExpenseSheet();
        ExpenseSheet updatedSheet = new ExpenseSheet();
        when(expenseSheetRepository.findById(1L)).thenReturn(Optional.of(existingSheet));
        when(expenseSheetRepository.save(updatedSheet)).thenReturn(updatedSheet);

        Optional<ExpenseSheet> result = expenseSheetService.updateExpenseSheet(1L, updatedSheet);

        assertTrue(result.isPresent());
        assertEquals(updatedSheet, result.get());
        verify(expenseSheetRepository, times(1)).save(updatedSheet);
    }

    @Test
    public void testUpdateExpenseSheet_NotFound() {
        ExpenseSheet updatedSheet = new ExpenseSheet();
        when(expenseSheetRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<ExpenseSheet> result = expenseSheetService.updateExpenseSheet(1L, updatedSheet);

        assertFalse(result.isPresent());
        verify(expenseSheetRepository, times(0)).save(any());
    }

    @Test
    public void testDeleteExpenseSheet() {
        ExpenseSheet sheet = new ExpenseSheet();
        when(expenseSheetRepository.findById(1L)).thenReturn(Optional.of(sheet));
        doNothing().when(expenseSheetRepository).delete(sheet);

        Optional<Object> result = expenseSheetService.deleteExpenseSheet(1L);

        assertTrue(result.isPresent());
        verify(expenseSheetRepository, times(1)).delete(sheet);
    }

    @Test
    public void testDeleteExpenseSheet_NotFound() {
        when(expenseSheetRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Object> result = expenseSheetService.deleteExpenseSheet(1L);

        assertFalse(result.isPresent());
        verify(expenseSheetRepository, times(0)).delete(any());
    }
}
