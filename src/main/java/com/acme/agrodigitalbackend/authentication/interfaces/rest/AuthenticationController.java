package com.acme.agrodigitalbackend.authentication.interfaces.rest;

import com.acme.agrodigitalbackend.authentication.domain.services.AuthenticationService;
import com.acme.agrodigitalbackend.authentication.interfaces.rest.resources.AuthenticationResponse;
import com.acme.agrodigitalbackend.authentication.interfaces.rest.resources.LoginResource;
import com.acme.agrodigitalbackend.authentication.interfaces.rest.transform.AuthenticationResponseFromEntityAssembler;
import com.acme.agrodigitalbackend.authentication.interfaces.rest.transform.LoginCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * AuthenticationController
 * <p>
 *     This class is the entry point for all the REST endpoints related to authentication.
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", methods = { RequestMethod.POST })
@Tag(name = "Authentication", description = "Authentication Endpoints")
public class AuthenticationController {
    
    private final AuthenticationService authenticationService;
    
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    
    /**
     * Authenticates a user with email and password
     * @param loginResource the resource containing login credentials
     * @return the authentication response with user information
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody LoginResource loginResource) {
        var loginCommand = LoginCommandFromResourceAssembler.toCommandFromResource(loginResource);
        var userOptional = authenticationService.authenticate(loginCommand);
        
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        var authenticationResponse = AuthenticationResponseFromEntityAssembler.toResourceFromEntity(userOptional.get());
        return ResponseEntity.ok(authenticationResponse);
    }
}