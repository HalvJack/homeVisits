package com.example.wizytydomowe.HereApi;

import com.here.account.oauth2.HereAccessTokenProvider;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
@Slf4j
@Getter
public class TokenRequester {

    private static final Logger logger = LoggerFactory.getLogger(TokenRequester.class);

    private final RestTemplate restTemplate;

    private String accessToken;


    @Value("${here.token.endpoint.url}")
    private String tokenEndpointUrl;

    public TokenRequester(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String requestAccessToken() {

        HereAccessTokenProvider accessTokens = HereAccessTokenProvider.builder().build();
        accessToken = accessTokens.getAccessToken();
        logger.info("Token response: {}", accessToken);

        return accessToken;
    }
}

