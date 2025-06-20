package com.acme.agrodigitalbackend.shared.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired(required = false)
    private AuthenticationEntryPoint unauthorizedRequestHandler;
    
    @Autowired(required = false)
    private Object authorizationRequestFilter;

    /**
     * This method creates the security filter chain.
     * It also configures the http security.
     *
     * @param http The http security
     * @return The security filter chain
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(configurer -> configurer.configurationSource(corsConfigurationSource()));
        
        http.csrf(csrfConfigurer -> csrfConfigurer.disable());
        
        if (unauthorizedRequestHandler != null) {
            http.exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(unauthorizedRequestHandler));
        }
        
        http.sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(
                                "/api/v1/authentication/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/swagger-resources/**",
                                "/webjars/**").permitAll()
                        .anyRequest().authenticated());

        // Add authentication provider if available
        try {
            http.authenticationProvider(authenticationProvider());
        } catch (Exception e) {
            // If authenticationProvider is not available, continue without it
        }
        
        // Add authorization filter if available
        if (authorizationRequestFilter != null && authorizationRequestFilter instanceof jakarta.servlet.Filter) {
            http.addFilterBefore((jakarta.servlet.Filter) authorizationRequestFilter, UsernamePasswordAuthenticationFilter.class);
        }        
        return http.build();
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Allow all origins - esto es importante para Railway y Swagger
        configuration.setAllowedOriginPatterns(List.of("*"));
        
        // Allow all common HTTP methods including OPTIONS for preflight
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "PATCH"));
        
        // Allow all headers
        configuration.setAllowedHeaders(List.of("*"));
        
        // Allow credentials
        configuration.setAllowCredentials(true);
        
        // Set max age for preflight requests
        configuration.setMaxAge(3600L);
        
        // Expose headers for client access
        configuration.setExposedHeaders(List.of("Authorization", "Content-Type", "X-Requested-With", "Accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        
        // Register configuration for all paths
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
        // This will be implemented by your authentication configuration
        return null;
    }
}