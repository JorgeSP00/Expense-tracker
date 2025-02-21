package com.example.expense_tracker.controller;

import com.example.expense_tracker.model.Category;
import com.example.expense_tracker.service.CategoryService;
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
public class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCategories_WhenCategoriesExist_ShouldReturnOk() {
        List<Category> categories = Arrays.asList(new Category(), new Category());
        when(categoryService.getAllCategories()).thenReturn(categories);

        ResponseEntity<List<Category>> response = categoryController.getAllCategories();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(categoryService, times(1)).getAllCategories();
    }

    @Test
    public void testGetAllCategories_WhenNoCategoriesExist_ShouldReturnNoContent() {
        when(categoryService.getAllCategories()).thenReturn(List.of());

        ResponseEntity<List<Category>> response = categoryController.getAllCategories();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(categoryService, times(1)).getAllCategories();
    }

    @Test
    public void testGetCategoryById_WhenCategoryExists_ShouldReturnOk() {
        Category category = new Category();
        when(categoryService.getCategoryById(1L)).thenReturn(Optional.of(category));

        ResponseEntity<Category> response = categoryController.getCategoryById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(category, response.getBody());
    }

    @Test
    public void testGetCategoryById_WhenCategoryDoesNotExist_ShouldReturnNotFound() {
        when(categoryService.getCategoryById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Category> response = categoryController.getCategoryById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(categoryService, times(1)).getCategoryById(1L);
    }

    @Test
    public void testCreateCategory_WhenValidCategory_ShouldReturnOk() {
        Category category = new Category();
        when(categoryService.createCategory(category)).thenReturn(category);

        ResponseEntity<Category> response = categoryController.createCategory(category);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(category, response.getBody());
        verify(categoryService, times(1)).createCategory(category);
    }

    @Test
    public void testCreateCategory_WhenCategoryIsNull_ShouldReturnBadRequest() {
        when(categoryService.createCategory(null)).thenReturn(null);

        ResponseEntity<Category> response = categoryController.createCategory(null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(categoryService, times(1)).createCategory(null);
    }

    @Test
    public void testUpdateCategory_WhenCategoryExists_ShouldReturnOk() {
        Category category = new Category();
        when(categoryService.updateCategory(1L, category)).thenReturn(Optional.of(category));

        ResponseEntity<Category> response = categoryController.updateCategory(1L, category);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(category, response.getBody());
    }

    @Test
    public void testUpdateCategory_WhenCategoryDoesNotExist_ShouldReturnNotFound() {
        Category category = new Category();
        when(categoryService.updateCategory(1L, category)).thenReturn(Optional.empty());

        ResponseEntity<Category> response = categoryController.updateCategory(1L, category);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(categoryService, times(1)).updateCategory(1L, category);
    }

    @Test
    public void testDeleteCategory_WhenCategoryExists_ShouldReturnNoContent() {
        when(categoryService.deleteCategory(1L)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = categoryController.deleteCategory(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(categoryService, times(1)).deleteCategory(1L);
    }

    @Test
    public void testDeleteCategory_WhenCategoryDoesNotExist_ShouldReturnNotFound() {
        when(categoryService.deleteCategory(1L)).thenReturn(Optional.ofNullable(null));

        ResponseEntity<Object> response = categoryController.deleteCategory(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(categoryService, times(1)).deleteCategory(1L);
    }
}