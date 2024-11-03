package com.example.expense_tracker.controller;

import com.example.expense_tracker.model.ExpenseSheet;
import com.example.expense_tracker.service.ExpenseSheetService;
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
public class ExpenseSheetControllerTest {

    @Mock
    private ExpenseSheetService expenseSheetService;

    @InjectMocks
    private ExpenseSheetController expenseSheetController;

    public ExpenseSheetControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllExpenseSheets() {
        List<ExpenseSheet> sheets = Arrays.asList(new ExpenseSheet(), new ExpenseSheet());
        when(expenseSheetService.getAllExpenseSheets()).thenReturn(sheets);

        List<ExpenseSheet> result = expenseSheetController.getAllExpenseSheets();

        assertEquals(2, result.size());
        verify(expenseSheetService, times(1)).getAllExpenseSheets();
    }

    @Test
    public void testGetExpenseSheetById() {
        ExpenseSheet sheet = new ExpenseSheet();
        when(expenseSheetService.getExpenseSheetById(1L)).thenReturn(Optional.of(sheet));

        ResponseEntity<ExpenseSheet> response = expenseSheetController.getExpenseSheetById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sheet, response.getBody());
    }

    @Test
    public void testGetExpenseSheetById_NotFound() {
        when(expenseSheetService.getExpenseSheetById(1L)).thenReturn(Optional.empty());

        ResponseEntity<ExpenseSheet> response = expenseSheetController.getExpenseSheetById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testCreateExpenseSheet() {
        ExpenseSheet sheet = new ExpenseSheet();
        when(expenseSheetService.createExpenseSheet(sheet)).thenReturn(sheet);

        ResponseEntity<ExpenseSheet> response = expenseSheetController.createExpenseSheet(sheet);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sheet, response.getBody());
    }

    @Test
    public void testUpdateExpenseSheet() {
        ExpenseSheet sheet = new ExpenseSheet();
        when(expenseSheetService.updateExpenseSheet(1L, sheet)).thenReturn(sheet);

        ResponseEntity<ExpenseSheet> response = expenseSheetController.updateExpenseSheet(1L, sheet);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sheet, response.getBody());
    }

    @Test
    public void testUpdateExpenseSheet_NotFound() {
        ExpenseSheet sheet = new ExpenseSheet();
        when(expenseSheetService.updateExpenseSheet(1L, sheet)).thenReturn(null);

        ResponseEntity<ExpenseSheet> response = expenseSheetController.updateExpenseSheet(1L, sheet);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteExpenseSheet() {
        doNothing().when(expenseSheetService).deleteExpenseSheet(1L);

        ResponseEntity<Void> response = expenseSheetController.deleteExpenseSheet(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(expenseSheetService, times(1)).deleteExpenseSheet(1L);
    }
}