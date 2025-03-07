package com.example.subscription_service.config;



import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter { // Use OncePerRequestFilter for Servlet-based apps

    @Autowired
    private JwtUtil jwtUtil; // You'll need to create JwtUtil in application-catalog-service

    private static final List<String> EXCLUDED_PATHS = List.of(
            "/api/applications/public-endpoint", // Example public endpoint, adjust as needed
            "/api/categories" // Example public endpoint, adjust as needed
            // Add other paths that should be excluded from JWT authentication in application-catalog-service
    );

    private static final Logger logger = Logger.getLogger(JwtAuthenticationFilter.class.getName());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // Skip JWT authentication for excluded paths
        if (EXCLUDED_PATHS.stream().anyMatch(path::startsWith)) {
            filterChain.doFilter(request, response);
            return;
        }

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            logger.warning("Missing or invalid Authorization header for path: " + path);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Return 401 Unauthorized
            response.getWriter().write("Missing or invalid Authorization header");
            return; // Do not continue filter chain
            // Or, if you want to allow unauthenticated access to other endpoints, you could just call:
            // filterChain.doFilter(request, response);
            // return;
        }


        // Clean the token
        String token = authorizationHeader.substring(7).trim(); // Extract token after "Bearer " prefix
        if (!jwtUtil.validateToken(token)) {
            logger.warning("Invalid JWT token for user: " + jwtUtil.extractUsername(token) + ", path: " + path);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // Return 403 Forbidden for invalid token
            response.getWriter().write("Invalid JWT token");
            return; // Do not continue filter chain
        }

        // Extract username and roles
        String username = jwtUtil.extractUsername(token);
        List<String> roles = jwtUtil.extractRoles(token);
        if (roles == null || roles.isEmpty() || (!roles.contains("USER") && !roles.contains("ADMIN"))) {
            logger.warning("Insufficient roles in JWT for user: " + username + ", path: " + path + ", roles: " + roles);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // Return 403 Forbidden for insufficient role
            response.getWriter().write("Insufficient role in JWT");
            return; // Do not continue filter chain
        }

        // Create authorities with ROLE_ prefix
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                username, null, authorities);

        // Set authentication in SecurityContextHolder (for Servlet-based apps)
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Continue filter chain
        filterChain.doFilter(request, response);
    }
}