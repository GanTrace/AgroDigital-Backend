package com.acme.agrodigitalbackend.authentication.interfaces.rest.resources;

/**
 * AuthenticationResponse
 * <p>
 *     This record represents the response after successful authentication,
 *     containing user information.
 * </p>
 */
public record AuthenticationResponse(
        Long id,
        String name,
        String email,
        String role,
        String profileImageUrl,
        String message
) {
}