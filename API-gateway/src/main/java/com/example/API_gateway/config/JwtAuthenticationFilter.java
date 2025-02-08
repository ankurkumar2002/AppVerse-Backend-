package com.example.API_gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter implements WebFilter, Ordered {

    @Autowired
    private JwtUtil jwtUtil;

    private static final List<String> EXCLUDED_PATHS = List.of("/auth/login", "/auth/signup", "/auth/forgot-password", "/auth/reset-password", "/login/oauth2/code/google", "/validate");
    private static final Logger logger = Logger.getLogger(JwtAuthenticationFilter.class.getName());

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        if (EXCLUDED_PATHS.contains(path)) {
            return chain.filter(exchange);
        }

        String authorizationHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null) {
            return Mono.error(new RuntimeException("Missing authorization header"));
        }

        // Clean the token
        String token = authorizationHeader.replaceAll("(?i)Bearer ", "").trim();
        if (!jwtUtil.validateToken(token)) {
            return Mono.error(new RuntimeException("Invalid token"));
        }

        // Extract username and roles
        String username = jwtUtil.extractUsername(token);
        List<String> roles = jwtUtil.extractRoles(token);
        if (roles == null || roles.isEmpty() || (!roles.contains("USER") && !roles.contains("ADMIN"))) {
            return Mono.error(new RuntimeException("Insufficient role"));
        }

        // Create authorities with ROLE_ prefix
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                username, null, authorities);

        // Create new Authorization header
        String cleanedAuthorization = "Bearer " + token;
        ServerHttpRequest mutatedRequest = request.mutate()
                .header(HttpHeaders.AUTHORIZATION, cleanedAuthorization)
                .build();

        ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();

        // Set authentication in context and proceed with mutated request
        return chain.filter(mutatedExchange)
                .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}