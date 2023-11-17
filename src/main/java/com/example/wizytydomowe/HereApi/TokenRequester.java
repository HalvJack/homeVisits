package com.example.wizytydomowe.HereApi;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@Slf4j
@Getter
public class TokenRequester {

    private static final Logger logger = LoggerFactory.getLogger(TokenRequester.class);

    // Inject the RestTemplate bean
    private final RestTemplate restTemplate;
    private final OAuthSignatureBuilder oAuthSignatureBuilder;


    @Value("${here.token.endpoint.url}")
    private String tokenEndpointUrl;

    public TokenRequester(RestTemplate restTemplate, OAuthSignatureBuilder oAuthSignatureBuilder) {
        this.restTemplate = restTemplate;
        this.oAuthSignatureBuilder = oAuthSignatureBuilder;
    }

    public String requestAccessToken() {
        // Construct the headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Construct the Authorization header
        String authorizationHeader = "OAuth";
        headers.set("Authorization", authorizationHeader);
        for(Map.Entry<String, String> entry : oAuthSignatureBuilder.getOAuthParameters().entrySet()){
            headers.add(entry.getKey(), entry.getValue());
        }
        headers.remove("grant_type");
        headers.add("oauth_signature", oAuthSignatureBuilder.getSignature());
        headers.add("authorization_header", oAuthSignatureBuilder.getAuthorizationHeader());
        // Construct the request body
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "client_credentials");

        // Construct the complete request entity
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        // Make the request and retrieve the response
        ResponseEntity<String> responseEntity = restTemplate.exchange(tokenEndpointUrl, HttpMethod.POST, requestEntity, String.class);

        // Log the response instead of printing to System.out
        String response = responseEntity.getBody();
        logger.info("Token response: {}", response);

        return response; // Return the access token or handle it as needed
    }
}

