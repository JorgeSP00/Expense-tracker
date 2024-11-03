package com.example.expense_tracker.controller;

import com.example.expense_tracker.model.Goal;
import com.example.expense_tracker.service.GoalService;
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
public class GoalControllerTest {

    @Mock
    private GoalService goalService;

    @InjectMocks
    private GoalController goalController;

    public GoalControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllGoals() {
        List<Goal> goals = Arrays.asList(new Goal(), new Goal());
        when(goalService.getAllGoals()).thenReturn(goals);

        List<Goal> result = goalController.getAllGoals();

        assertEquals(2, result.size());
        verify(goalService, times(1)).getAllGoals();
    }

    @Test
    public void testGetGoalById() {
        Goal goal = new Goal();
        when(goalService.getGoalById(1L)).thenReturn(Optional.of(goal));

        ResponseEntity<Goal> response = goalController.getGoalById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(goal, response.getBody());
    }

    @Test
    public void testGetGoalById_NotFound() {
        when(goalService.getGoalById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Goal> response = goalController.getGoalById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testCreateGoal() {
        Goal goal = new Goal();
        when(goalService.createGoal(goal)).thenReturn(goal);

        Goal result = goalController.createGoal(goal);

        assertEquals(goal, result);
        verify(goalService, times(1)).createGoal(goal);
    }

    @Test
    public void testUpdateGoal() {
        Goal goal = new Goal();
        when(goalService.updateGoal(1L, goal)).thenReturn(goal);

        ResponseEntity<Goal> response = goalController.updateGoal(1L, goal);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(goal, response.getBody());
    }

    @Test
    public void testUpdateGoal_NotFound() {
        Goal goal = new Goal();
        when(goalService.updateGoal(1L, goal)).thenReturn(null);

        ResponseEntity<Goal> response = goalController.updateGoal(1L, goal);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteGoal() {
        doNothing().when(goalService).deleteGoal(1L);

        ResponseEntity<Void> response = goalController.deleteGoal(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(goalService, times(1)).deleteGoal(1L);
    }
}