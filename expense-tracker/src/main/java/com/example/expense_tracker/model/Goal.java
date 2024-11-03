package com.example.expense_tracker.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "goals")
@Data
@Schema(description = "Represents a specific savings or spending goal associated with a category")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the goal", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Target amount to reach for the goal", example = "1000.00")
    private double targetAmount;

    @Schema(description = "Deadline date to reach the goal", example = "2023-12-31", type = "string", format = "date")
    private Date targetDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @Schema(description = "Category associated with the goal")
    private Category category;
}