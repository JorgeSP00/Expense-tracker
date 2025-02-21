package com.example.expense_tracker.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "expenses")
@Data
@Schema(description = "Represents an individual expense associated with an expense sheet")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the expense", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Amount of the expense", example = "59.99")
    private double amount;

    @Schema(description = "Detailed description of the expense", example = "Dinner at a restaurant")
    private String description;

    @Schema(description = "Date when the expense occurred", example = "2023-11-01", type = "string", format = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "sheet_id")
    @Schema(description = "Expense sheet to which this expense belongs")
    private ExpenseSheet expenseSheet;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    @Schema(description = "Category to which this expense belongs")
    private Category category;
}