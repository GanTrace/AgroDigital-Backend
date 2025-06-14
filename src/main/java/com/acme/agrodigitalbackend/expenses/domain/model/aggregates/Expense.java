package com.acme.agrodigitalbackend.expenses.domain.model.aggregates;

import com.acme.agrodigitalbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.agrodigitalbackend.expenses.domain.model.commands.CreateExpenseCommand;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "expenses")
public class Expense extends AuditableAbstractAggregateRoot<Expense> {

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    @NotNull
    @Positive
    private BigDecimal amount;

    @Column(name = "description", nullable = false)
    @NotBlank
    private String description;

    @Column(name = "category", nullable = false)
    @NotBlank
    private String category; // "Veterinario", "Alimentación", "Equipamiento", "Suministros", "Mantenimiento", "Otros Gastos"

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDateTime date;

    @Column(name = "type", nullable = false)
    @NotBlank
    private String type; // "expense"

    @Column(name = "user_id", nullable = false)
    @NotNull
    private Long userId;

    @Column(name = "user_name", nullable = false)
    @NotBlank
    private String userName;

    // Constructor por defecto
    public Expense() {
        this.type = "expense";
    }

    // Constructor con comando
    public Expense(CreateExpenseCommand command) {
        this();
        this.amount = command.amount();
        this.description = command.description();
        this.category = validateCategory(command.category());
        this.date = command.date();
        this.userId = command.userId();
        this.userName = command.userName();
    }

    // Validación de categoría
    private String validateCategory(String category) {
        if (!isValidCategory(category)) {
            throw new IllegalArgumentException("Invalid category: " + category + ". Valid categories are: Veterinario, Alimentación, Equipamiento, Suministros, Mantenimiento, Otros Gastos");
        }
        return category;
    }

    // Verificar si la categoría es válida
    public static boolean isValidCategory(String category) {
        return category != null && (
            category.equals("Veterinario") ||
            category.equals("Alimentación") ||
            category.equals("Equipamiento") ||
            category.equals("Suministros") ||
            category.equals("Mantenimiento") ||
            category.equals("Otros Gastos")
        );
    }

    // Getters
    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    // Setters
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = validateCategory(category);
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}