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

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void testGetExpenseSheetById() {
        ExpenseSheet sheet = new ExpenseSheet();
        when(expenseSheetRepository.findById(1L)).thenReturn(Optional.of(sheet));

        Optional<ExpenseSheet> result = expenseSheetService.getExpenseSheetById(1L);

        assertEquals(Optional.of(sheet), result);
        verify(expenseSheetRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateExpenseSheet() {
        ExpenseSheet sheet = new ExpenseSheet();
        when(expenseSheetRepository.save(sheet)).thenReturn(sheet);

        ExpenseSheet result = expenseSheetService.createExpenseSheet(sheet);

        assertEquals(sheet, result);
        verify(expenseSheetRepository, times(1)).save(sheet);
    }

    @Test
    public void testUpdateExpenseSheet() {
        ExpenseSheet sheet = new ExpenseSheet();
        when(expenseSheetRepository.existsById(1L)).thenReturn(true);
        when(expenseSheetRepository.save(sheet)).thenReturn(sheet);

        ExpenseSheet result = expenseSheetService.updateExpenseSheet(1L, sheet);

        assertEquals(sheet, result);
        verify(expenseSheetRepository, times(1)).save(sheet);
    }

    @Test
    public void testDeleteExpenseSheet() {
        doNothing().when(expenseSheetRepository).deleteById(1L);

        expenseSheetService.deleteExpenseSheet(1L);

        verify(expenseSheetRepository, times(1)).deleteById(1L);
    }
}