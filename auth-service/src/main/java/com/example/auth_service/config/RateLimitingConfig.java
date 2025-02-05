package com.example.auth_service.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;

@Configuration
public class RateLimitingConfig {
    // @Bean
    // public RateLimiterRegistry rateLimiterRegistry(){
    //     RateLimiterConfig config = RateLimiterConfig.custom()
    //                                                 .limitRefreshPeriod(Duration.ofSeconds(10))
    //                                                 .limitForPeriod(5)
    //                                                 .timeoutDuration(Duration.ofMillis(500))
    //                                                 .build();

    //     return RateLimiterRegistry.of(config);
    // }
}
