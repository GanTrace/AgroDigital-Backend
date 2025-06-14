package com.acme.agrodigitalbackend.expenses.infrastructure.persistence.jpa.repositories;

import com.acme.agrodigitalbackend.expenses.domain.model.aggregates.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserId(Long userId);
    List<Expense> findByCategory(String category);
    List<Expense> findByUserIdAndCategory(Long userId, String category);
}