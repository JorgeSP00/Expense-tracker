package com.example.expense_tracker.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
@Data
@Schema(description = "Represents an expense category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the category", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Name of the category", example = "Food")
    private String name;

    @Schema(description = "Description of the category", example = "Expenses related to food and dining")
    private String description;
}