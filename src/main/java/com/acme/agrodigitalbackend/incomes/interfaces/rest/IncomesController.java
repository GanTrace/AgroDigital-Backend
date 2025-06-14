package com.acme.agrodigitalbackend.incomes.interfaces.rest;

import com.acme.agrodigitalbackend.incomes.domain.model.queries.GetAllIncomesQuery;
import com.acme.agrodigitalbackend.incomes.domain.model.queries.GetIncomeByIdQuery;
import com.acme.agrodigitalbackend.incomes.domain.model.queries.GetIncomesByUserIdQuery;
import com.acme.agrodigitalbackend.incomes.domain.services.IncomeCommandService;
import com.acme.agrodigitalbackend.incomes.domain.services.IncomeQueryService;
import com.acme.agrodigitalbackend.incomes.interfaces.rest.resources.CreateIncomeResource;
import com.acme.agrodigitalbackend.incomes.interfaces.rest.resources.IncomeResource;
import com.acme.agrodigitalbackend.incomes.interfaces.rest.transform.CreateIncomeCommandFromResourceAssembler;
import com.acme.agrodigitalbackend.incomes.interfaces.rest.transform.IncomeResourceFromEntityAssembler;
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
@RequestMapping(value = "/api/v1/incomes", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Incomes", description = "Income Management Endpoints")
public class IncomesController {
    private final IncomeCommandService incomeCommandService;
    private final IncomeQueryService incomeQueryService;
    private final UsersContextFacade usersContextFacade;

    public IncomesController(IncomeCommandService incomeCommandService, IncomeQueryService incomeQueryService, UsersContextFacade usersContextFacade) {
        this.incomeCommandService = incomeCommandService;
        this.incomeQueryService = incomeQueryService;
        this.usersContextFacade = usersContextFacade;
    }

    @PostMapping
    public ResponseEntity<IncomeResource> createIncome(@RequestBody CreateIncomeResource createIncomeResource) {
        // Validar que el usuario existe
        var user = usersContextFacade.fetchUserById(createIncomeResource.userId());
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var createIncomeCommand = CreateIncomeCommandFromResourceAssembler.toCommandFromResource(createIncomeResource);
        var incomeId = incomeCommandService.handle(createIncomeCommand);
        if (incomeId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getIncomeByIdQuery = new GetIncomeByIdQuery(incomeId);
        var income = incomeQueryService.handle(getIncomeByIdQuery);
        if (income.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var incomeResource = IncomeResourceFromEntityAssembler.toResourceFromEntity(income.get());
        return new ResponseEntity<>(incomeResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<IncomeResource>> getAllIncomes() {
        var getAllIncomesQuery = new GetAllIncomesQuery();
        var incomes = incomeQueryService.handle(getAllIncomesQuery);
        var incomeResources = incomes.stream().map(IncomeResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(incomeResources);
    }

    @GetMapping("/{incomeId}")
    public ResponseEntity<IncomeResource> getIncomeById(@PathVariable Long incomeId) {
        var getIncomeByIdQuery = new GetIncomeByIdQuery(incomeId);
        var income = incomeQueryService.handle(getIncomeByIdQuery);
        if (income.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var incomeResource = IncomeResourceFromEntityAssembler.toResourceFromEntity(income.get());
        return ResponseEntity.ok(incomeResource);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<IncomeResource>> getIncomesByUserId(@PathVariable Long userId) {
        var getIncomesByUserIdQuery = new GetIncomesByUserIdQuery(userId);
        var incomes = incomeQueryService.handle(getIncomesByUserIdQuery);
        var incomeResources = incomes.stream().map(IncomeResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(incomeResources);
    }
}