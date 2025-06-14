package com.acme.agrodigitalbackend.expenses.interfaces.rest;

import com.acme.agrodigitalbackend.expenses.domain.model.commands.DeleteExpenseCommand;
import com.acme.agrodigitalbackend.expenses.domain.model.queries.GetAllExpensesQuery;
import com.acme.agrodigitalbackend.expenses.domain.model.queries.GetExpenseByIdQuery;
import com.acme.agrodigitalbackend.expenses.domain.model.queries.GetExpensesByUserIdQuery;
import com.acme.agrodigitalbackend.expenses.domain.services.ExpenseCommandService;
import com.acme.agrodigitalbackend.expenses.domain.services.ExpenseQueryService;
import com.acme.agrodigitalbackend.expenses.interfaces.rest.resources.CreateExpenseResource;
import com.acme.agrodigitalbackend.expenses.interfaces.rest.resources.ExpenseResource;
import com.acme.agrodigitalbackend.expenses.interfaces.rest.transform.CreateExpenseCommandFromResourceAssembler;
import com.acme.agrodigitalbackend.expenses.interfaces.rest.transform.ExpenseResourceFromEntityAssembler;
import com.acme.agrodigitalbackend.users.interfaces.acl.UsersContextFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/expenses", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Expenses", description = "Expense Management Endpoints")
public class ExpensesController {
    private final ExpenseCommandService expenseCommandService;
    private final ExpenseQueryService expenseQueryService;
    private final UsersContextFacade usersContextFacade;

    public ExpensesController(ExpenseCommandService expenseCommandService, ExpenseQueryService expenseQueryService, UsersContextFacade usersContextFacade) {
        this.expenseCommandService = expenseCommandService;
        this.expenseQueryService = expenseQueryService;
        this.usersContextFacade = usersContextFacade;
    }

    @PostMapping
    public ResponseEntity<ExpenseResource> createExpense(@RequestBody CreateExpenseResource createExpenseResource) {
        // Validar que el usuario existe
        var user = usersContextFacade.fetchUserById(createExpenseResource.userId());
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var createExpenseCommand = CreateExpenseCommandFromResourceAssembler.toCommandFromResource(createExpenseResource);
        var expenseId = expenseCommandService.handle(createExpenseCommand);
        if (expenseId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getExpenseByIdQuery = new GetExpenseByIdQuery(expenseId);
        var expense = expenseQueryService.handle(getExpenseByIdQuery);
        if (expense.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var expenseResource = ExpenseResourceFromEntityAssembler.toResourceFromEntity(expense.get());
        return new ResponseEntity<>(expenseResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResource>> getAllExpenses() {
        var getAllExpensesQuery = new GetAllExpensesQuery();
        var expenses = expenseQueryService.handle(getAllExpensesQuery);
        var expenseResources = expenses.stream().map(ExpenseResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(expenseResources);
    }

    @GetMapping("/{expenseId}")
    public ResponseEntity<ExpenseResource> getExpenseById(@PathVariable Long expenseId) {
        var getExpenseByIdQuery = new GetExpenseByIdQuery(expenseId);
        var expense = expenseQueryService.handle(getExpenseByIdQuery);
        if (expense.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var expenseResource = ExpenseResourceFromEntityAssembler.toResourceFromEntity(expense.get());
        return ResponseEntity.ok(expenseResource);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ExpenseResource>> getExpensesByUserId(@PathVariable Long userId) {
        var getExpensesByUserIdQuery = new GetExpensesByUserIdQuery(userId);
        var expenses = expenseQueryService.handle(getExpensesByUserIdQuery);
        var expenseResources = expenses.stream().map(ExpenseResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(expenseResources);
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long expenseId) {
        var deleteExpenseCommand = new DeleteExpenseCommand(expenseId);
        expenseCommandService.handle(deleteExpenseCommand);
        return ResponseEntity.noContent().build();
    }
}