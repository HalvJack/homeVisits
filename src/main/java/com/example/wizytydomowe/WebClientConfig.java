package com.example.wizytydomowe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig implements WebFluxConfigurer {
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/maptiles")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET");
    }
}
