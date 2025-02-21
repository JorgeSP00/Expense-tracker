package com.example.expense_tracker.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "expense_sheets")
@Data
@Schema(description = "Represents an expense sheet that groups multiple individual expenses")
public class ExpenseSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the expense sheet", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Name or title of the expense sheet", example = "Paris Trip Expenses")
    private String name;

    @Schema(description = "General description of the expense sheet", example = "All expenses related to the Paris trip")
    private String description;

    @Schema(description = "Start date of the expense sheet period", example = "2023-10-01", type = "string", format = "date")
    private Date startDate;

    @Schema(description = "End date of the expense sheet period", example = "2023-10-07", type = "string", format = "date")
    private Date endDate;
}