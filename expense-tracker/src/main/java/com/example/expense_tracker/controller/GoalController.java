package com.example.expense_tracker.controller;

import com.example.expense_tracker.model.Goal;
import com.example.expense_tracker.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<Goal>> getAllGoals() {
        return Optional.ofNullable(goalService.getAllGoals())
                .filter(goals -> !goals.isEmpty())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
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
    public ResponseEntity<Goal> createGoal(@RequestBody Goal goal) {
        return Optional.ofNullable(goalService.createGoal(goal))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
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
        return goalService.updateGoal(id, goal)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * @swagger Delete a goal by ID
     * @operationId deleteGoal
     * @tags Goal
     * @parameter id path required - ID of the goal to delete
     * @response 204 No content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGoal(@PathVariable Long id) {
        return goalService.deleteGoal(id)
                .map(deleted -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }
}