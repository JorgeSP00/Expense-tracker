package com.example.expense_tracker.service;

import com.example.expense_tracker.model.Goal;
import com.example.expense_tracker.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;

    /**
     * @swagger Retrieve all goals
     * @operationId getAllGoals
     * @tags Goal
     * @response 200 List of Goal objects
     */
    public List<Goal> getAllGoals() {
        return goalRepository.findAll();
    }

    /**
     * @swagger Retrieve a goal by its ID
     * @operationId getGoalById
     * @tags Goal
     * @parameter id path required - Goal ID
     * @response 200 Goal object
     * @response 404 Not found
     */
    public Optional<Goal> getGoalById(Long id) {
        return goalRepository.findById(id);
    }

    /**
     * @swagger Create a new goal
     * @operationId createGoal
     * @tags Goal
     * @requestBody Goal object
     * @response 201 Created Goal object
     */
    public Goal createGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    /**
     * @swagger Update an existing goal by ID
     * @operationId updateGoal
     * @tags Goal
     * @parameter id path required - Goal ID
     * @requestBody Updated Goal object
     * @response 200 Updated Goal object
     * @response 404 Not found
     */
    public Goal updateGoal(Long id, Goal updatedGoal) {
        if (goalRepository.existsById(id)) {
            updatedGoal.setId(id);
            return goalRepository.save(updatedGoal);
        }
        return null;
    }

    /**
     * @swagger Delete a goal by ID
     * @operationId deleteGoal
     * @tags Goal
     * @parameter id path required - Goal ID
     * @response 204 No content
     */
    public void deleteGoal(Long id) {
        goalRepository.deleteById(id);
    }
}