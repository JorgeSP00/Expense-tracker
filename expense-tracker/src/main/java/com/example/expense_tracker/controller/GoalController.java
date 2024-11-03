package com.example.expense_tracker.controller;

import com.example.expense_tracker.model.Goal;
import com.example.expense_tracker.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
public class GoalController {

    @Autowired
    private GoalService goalService;

    /**
     * @swagger Get all goals
     * @operationId getAllGoals
     * @tags Goal
     * @response 200 List of goals
     */
    @GetMapping
    public List<Goal> getAllGoals() {
        return goalService.getAllGoals();
    }

    /**
     * @swagger Get a goal by ID
     * @operationId getGoalById
     * @tags Goal
     * @parameter id path required - ID of the goal
     * @response 200 Goal object
     * @response 404 Not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Goal> getGoalById(@PathVariable Long id) {
        return goalService.getGoalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * @swagger Create a new goal
     * @operationId createGoal
     * @tags Goal
     * @requestBody Goal object to create
     * @response 201 Created goal object
     */
    @PostMapping
    public Goal createGoal(@RequestBody Goal goal) {
        return goalService.createGoal(goal);
    }

    /**
     * @swagger Update an existing goal by ID
     * @operationId updateGoal
     * @tags Goal
     * @parameter id path required - ID of the goal to update
     * @requestBody Updated goal object
     * @response 200 Updated goal object
     * @response 404 Not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Goal> updateGoal(@PathVariable Long id, @RequestBody Goal goal) {
        Goal updatedGoal = goalService.updateGoal(id, goal);
        return updatedGoal != null ? ResponseEntity.ok(updatedGoal) : ResponseEntity.notFound().build();
    }

    /**
     * @swagger Delete a goal by ID
     * @operationId deleteGoal
     * @tags Goal
     * @parameter id path required - ID of the goal to delete
     * @response 204 No content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id);
        return ResponseEntity.noContent().build();
    }
}