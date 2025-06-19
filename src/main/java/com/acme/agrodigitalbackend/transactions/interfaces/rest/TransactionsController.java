package com.acme.agrodigitalbackend.transactions.interfaces.rest;

import com.acme.agrodigitalbackend.expenses.domain.services.ExpenseQueryService;
import com.acme.agrodigitalbackend.expenses.domain.services.ExpenseCommandService;
import com.acme.agrodigitalbackend.expenses.domain.model.queries.GetExpensesByUserIdQuery;
import com.acme.agrodigitalbackend.expenses.domain.model.commands.DeleteExpenseCommand;
import com.acme.agrodigitalbackend.incomes.domain.services.IncomeQueryService;
import com.acme.agrodigitalbackend.incomes.domain.services.IncomeCommandService;
import com.acme.agrodigitalbackend.incomes.domain.model.queries.GetIncomesByUserIdQuery;
import com.acme.agrodigitalbackend.incomes.domain.model.commands.DeleteIncomeCommand;
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

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Transactions", description = "Transaction Management Endpoints")
public class TransactionsController {
    private final IncomeQueryService incomeQueryService;
    private final ExpenseQueryService expenseQueryService;
    private final IncomeCommandService incomeCommandService;
    private final ExpenseCommandService expenseCommandService;

    public TransactionsController(IncomeQueryService incomeQueryService, ExpenseQueryService expenseQueryService,
                                IncomeCommandService incomeCommandService, ExpenseCommandService expenseCommandService) {
        this.incomeQueryService = incomeQueryService;
        this.expenseQueryService = expenseQueryService;
        this.incomeCommandService = incomeCommandService;
        this.expenseCommandService = expenseCommandService;
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

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long transactionId, @RequestParam String type) {
        try {
            if ("income".equalsIgnoreCase(type)) {
                var deleteIncomeCommand = new DeleteIncomeCommand(transactionId);
                incomeCommandService.handle(deleteIncomeCommand);
            } else if ("expense".equalsIgnoreCase(type)) {
                var deleteExpenseCommand = new DeleteExpenseCommand(transactionId);
                expenseCommandService.handle(deleteExpenseCommand);
            } else {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}