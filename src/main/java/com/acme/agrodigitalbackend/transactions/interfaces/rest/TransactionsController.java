package com.acme.agrodigitalbackend.transactions.interfaces.rest;

import com.acme.agrodigitalbackend.expenses.domain.services.ExpenseQueryService;
import com.acme.agrodigitalbackend.expenses.domain.model.queries.GetExpensesByUserIdQuery;
import com.acme.agrodigitalbackend.incomes.domain.services.IncomeQueryService;
import com.acme.agrodigitalbackend.incomes.domain.model.queries.GetIncomesByUserIdQuery;
import com.acme.agrodigitalbackend.transactions.interfaces.rest.resources.TransactionResource;
import com.acme.agrodigitalbackend.transactions.interfaces.rest.transform.TransactionResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET })
@RestController
@RequestMapping(value = "/api/v1/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Transactions", description = "Transaction Management Endpoints")
public class TransactionsController {
    private final IncomeQueryService incomeQueryService;
    private final ExpenseQueryService expenseQueryService;

    public TransactionsController(IncomeQueryService incomeQueryService, ExpenseQueryService expenseQueryService) {
        this.incomeQueryService = incomeQueryService;
        this.expenseQueryService = expenseQueryService;
    }

    @GetMapping("/user/{userId}/recent")
    public ResponseEntity<List<TransactionResource>> getRecentTransactionsByUserId(@PathVariable Long userId, @RequestParam(defaultValue = "10") int limit) {
        // Obtener ingresos del usuario
        var getIncomesByUserIdQuery = new GetIncomesByUserIdQuery(userId);
        var incomes = incomeQueryService.handle(getIncomesByUserIdQuery);
        
        // Obtener gastos del usuario
        var getExpensesByUserIdQuery = new GetExpensesByUserIdQuery(userId);
        var expenses = expenseQueryService.handle(getExpensesByUserIdQuery);
        
        // Combinar y convertir a TransactionResource
        List<TransactionResource> transactions = new ArrayList<>();
        
        // Agregar ingresos
        transactions.addAll(incomes.stream()
            .map(TransactionResourceFromEntityAssembler::toResourceFromIncomeEntity)
            .collect(Collectors.toList()));
        
        // Agregar gastos
        transactions.addAll(expenses.stream()
            .map(TransactionResourceFromEntityAssembler::toResourceFromExpenseEntity)
            .collect(Collectors.toList()));
        
        // Ordenar por fecha (más recientes primero) y limitar
        var recentTransactions = transactions.stream()
            .sorted(Comparator.comparing(TransactionResource::date).reversed())
            .limit(limit)
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(recentTransactions);
    }
}