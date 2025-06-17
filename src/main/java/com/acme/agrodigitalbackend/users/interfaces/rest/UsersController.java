package com.acme.agrodigitalbackend.users.interfaces.rest;

import com.acme.agrodigitalbackend.users.domain.model.queries.GetAllUsersQuery;
import com.acme.agrodigitalbackend.users.domain.model.queries.GetUserByIdQuery;
import com.acme.agrodigitalbackend.users.domain.services.UserCommandService;
import com.acme.agrodigitalbackend.users.domain.services.UserQueryService;
import com.acme.agrodigitalbackend.users.interfaces.rest.resources.CreateUserResource;
import com.acme.agrodigitalbackend.users.interfaces.rest.resources.UpdateUserResource;
import com.acme.agrodigitalbackend.users.interfaces.rest.resources.UserResource;
import com.acme.agrodigitalbackend.users.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import com.acme.agrodigitalbackend.users.interfaces.rest.transform.UpdateUserCommandFromResourceAssembler;
import com.acme.agrodigitalbackend.users.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * UsersController
 * <p>
 *     This class is the entry point for all the REST endpoints related to the User entity.
 * </p>
 */

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
@Tag(name = "Users", description = "User Management Endpoints")
public class UsersController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public UsersController(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    /**
     * Creates a new user
     * @param createUserResource the resource containing the user data
     * @return the created user resource
     */
    @PostMapping
    public ResponseEntity<UserResource> createUser(@Valid @RequestBody CreateUserResource createUserResource) {
        var createUserCommand = CreateUserCommandFromResourceAssembler.toCommandFromResource(createUserResource);
        var user = userCommandService.handle(createUserCommand);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }

    /**
     * Gets all users
     * @return the list of all users
     */
    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var getAllUsersQuery = new GetAllUsersQuery();
        var users = userQueryService.handle(getAllUsersQuery);
        var userResources = users.stream()
                .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userResources);
    }

    /**
     * Gets a user by id
     * @param userId the user id
     * @return the user resource
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }

    /**
     * Updates an existing user
     * @param userId the ID of the user to update
     * @param updateUserResource the resource containing the updated user data
     * @return the updated user resource
     */
    @PatchMapping("/{userId}")
    public ResponseEntity<UserResource> updateUser(@PathVariable Long userId, @Valid @RequestBody UpdateUserResource updateUserResource) {
        var updateUserCommand = UpdateUserCommandFromResourceAssembler.toCommandFromResource(userId, updateUserResource);
        var user = userCommandService.handle(updateUserCommand);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }
}