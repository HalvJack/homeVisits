package com.example.wizytydomowe;

import com.example.wizytydomowe.HereApi.OAuthSignatureBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    public OAuthSignatureBuilder oauthSignatureBuilder(@Value("${here.access.key.id}") String accessKeyId, @Value("${here.access.key.secret}") String secretKey) {
        return new OAuthSignatureBuilder(accessKeyId, secretKey);
    }
}
