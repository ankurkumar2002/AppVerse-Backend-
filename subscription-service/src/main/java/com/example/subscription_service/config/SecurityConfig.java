package com.example.subscription_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity // Use @EnableWebSecurity for Servlet-based app
public class SecurityConfig { // You can rename this to SubscriptionServiceSecurityConfig

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter; // Inject your JwtAuthenticationFilter (Servlet-based)

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // Use SecurityFilterChain for Servlet-based app
        return http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for API (configure properly for production)
            // .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Enable CORS configuration
            .authorizeHttpRequests(exchanges -> exchanges
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Allow CORS preflight requests
                .requestMatchers(
                    "/api/subscriptions/public-endpoint",
                    "/api/categories" // Example public endpoint - adjust as needed
                    // Add other paths in subscription-service to permitAll() if needed
                ).permitAll()
                //.requestMatchers("/api/subscriptions/admin/**").hasRole("ADMIN") // Example admin-only path prefix (adjust as needed)
                .anyRequest().authenticated() // All other requests under /api require authentication
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Register JwtAuthenticationFilter
            .httpBasic(httpBasic -> httpBasic.disable()) // Disable HttpBasic auth
            .sessionManagement(sessionManagement -> sessionManagement.disable()) // Optional: Disable session management if stateless API
            .build();
    }

    // @Bean
    // public CorsConfigurationSource corsConfigurationSource() {
    //     CorsConfiguration config = new CorsConfiguration();
    //     config.setAllowedOrigins(List.of("http://localhost:4200")); // Allow requests from your Angular frontend (adjust origin as needed)
    //     config.setAllowedMethods(List.of( // Allowed HTTP methods
    //         HttpMethod.GET.name(),
    //         HttpMethod.POST.name(),
    //         HttpMethod.PUT.name(),
    //         HttpMethod.DELETE.name(),
    //         HttpMethod.OPTIONS.name()
    //     ));
    //     config.setAllowedHeaders(List.of("*")); // Allow all headers
    //     config.setAllowCredentials(true); // Allow sending credentials (cookies, authorization headers)
    //     config.setExposedHeaders(List.of("Authorization")); // Expose Authorization header in CORS response

    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     source.registerCorsConfiguration("/**", config); // Apply CORS config to all paths
    //     return source;
    // }
}