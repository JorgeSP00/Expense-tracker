package com.example.expense_tracker.service;

import com.example.expense_tracker.model.Goal;
import com.example.expense_tracker.repository.GoalRepository;
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
public class GoalServiceTest {

    @Mock
    private GoalRepository goalRepository;

    @InjectMocks
    private GoalService goalService;

    public GoalServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllGoals() {
        List<Goal> goals = Arrays.asList(new Goal(), new Goal());
        when(goalRepository.findAll()).thenReturn(goals);

        List<Goal> result = goalService.getAllGoals();

        assertEquals(2, result.size());
        verify(goalRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllGoals_EmptyList() {
        when(goalRepository.findAll()).thenReturn(List.of());

        List<Goal> result = goalService.getAllGoals();

        assertTrue(result.isEmpty());
        verify(goalRepository, times(1)).findAll();
    }

    @Test
    public void testGetGoalById() {
        Goal goal = new Goal();
        when(goalRepository.findById(1L)).thenReturn(Optional.of(goal));

        Optional<Goal> result = goalService.getGoalById(1L);

        assertTrue(result.isPresent());
        assertEquals(goal, result.get());
        verify(goalRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetGoalById_NotFound() {
        when(goalRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Goal> result = goalService.getGoalById(1L);

        assertFalse(result.isPresent());
        verify(goalRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateGoal() {
        Goal goal = new Goal();
        when(goalRepository.save(goal)).thenReturn(goal);

        Goal result = goalService.createGoal(goal);

        assertNotNull(result);
        assertEquals(goal, result);
        verify(goalRepository, times(1)).save(goal);
    }

    @Test
    public void testUpdateGoal() {
        Goal existingGoal = new Goal();
        Goal updatedGoal = new Goal();
        when(goalRepository.findById(1L)).thenReturn(Optional.of(existingGoal));
        when(goalRepository.save(updatedGoal)).thenReturn(updatedGoal);

        Optional<Goal> result = goalService.updateGoal(1L, updatedGoal);

        assertTrue(result.isPresent());
        assertEquals(updatedGoal, result.get());
        verify(goalRepository, times(1)).save(updatedGoal);
    }

    @Test
    public void testUpdateGoal_NotFound() {
        Goal updatedGoal = new Goal();
        when(goalRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Goal> result = goalService.updateGoal(1L, updatedGoal);

        assertFalse(result.isPresent());
        verify(goalRepository, times(0)).save(any());
    }

    @Test
    public void testDeleteGoal() {
        Goal goal = new Goal();
        when(goalRepository.findById(1L)).thenReturn(Optional.of(goal));
        doNothing().when(goalRepository).delete(goal);

        Optional<Object> result = goalService.deleteGoal(1L);

        assertTrue(result.isPresent());
        verify(goalRepository, times(1)).delete(goal);
    }

    @Test
    public void testDeleteGoal_NotFound() {
        when(goalRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Object> result = goalService.deleteGoal(1L);

        assertFalse(result.isPresent());
        verify(goalRepository, times(0)).delete(any());
    }
}