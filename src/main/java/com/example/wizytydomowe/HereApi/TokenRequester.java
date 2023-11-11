package com.example.wizytydomowe.HereApi;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
@Component
@Slf4j
public class TokenRequester {

    private static final Logger logger = LoggerFactory.getLogger(TokenRequester.class);

    // Inject the RestTemplate bean
    private final RestTemplate restTemplate;

    @Value("${here.access.key.id}")
    private String accessKeyId;

    @Value("${here.access.key.secret}")
    private String accessKeySecret;

    @Value("${here.token.endpoint.url}")
    private String tokenEndpointUrl;

    // Inject the RestTemplate bean through constructor
    public TokenRequester(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String requestAccessToken() {
        RestTemplate restTemplate = new RestTemplate();

        // Construct the Authorization header
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(accessKeyId, accessKeySecret);

        // Construct the request body
        String requestBody = "grant_type=client_credentials";

        // Construct the complete request entity
        RequestEntity<String> requestEntity = RequestEntity
                .post(URI.create(tokenEndpointUrl))
                .headers(headers)
                .body(requestBody);

        // Make the request and retrieve the response
        String response = restTemplate.exchange(requestEntity, String.class).getBody();

        // Log the response instead of printing to System.out
        logger.info("Token response: {}", response);


        return response; // Return the access token or handle it as needed
    }
}

