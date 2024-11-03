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

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void testGetGoalById() {
        Goal goal = new Goal();
        when(goalRepository.findById(1L)).thenReturn(Optional.of(goal));

        Optional<Goal> result = goalService.getGoalById(1L);

        assertEquals(Optional.of(goal), result);
        verify(goalRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateGoal() {
        Goal goal = new Goal();
        when(goalRepository.save(goal)).thenReturn(goal);

        Goal result = goalService.createGoal(goal);

        assertEquals(goal, result);
        verify(goalRepository, times(1)).save(goal);
    }

    @Test
    public void testUpdateGoal() {
        Goal goal = new Goal();
        when(goalRepository.existsById(1L)).thenReturn(true);
        when(goalRepository.save(goal)).thenReturn(goal);

        Goal result = goalService.updateGoal(1L, goal);

        assertEquals(goal, result);
        verify(goalRepository, times(1)).save(goal);
    }

    @Test
    public void testDeleteGoal() {
        doNothing().when(goalRepository).deleteById(1L);

        goalService.deleteGoal(1L);

        verify(goalRepository, times(1)).deleteById(1L);
    }
}