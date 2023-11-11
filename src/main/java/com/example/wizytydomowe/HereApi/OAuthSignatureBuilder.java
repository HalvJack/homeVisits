package com.example.wizytydomowe.HereApi;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

public class OAuthSignatureBuilder {
    private static final String UTF_8 = "UTF-8";
    private static final String HMAC_SHA256 = "HmacSHA256";
    private long timestamp;
    private String nonce;
    private SortedMap<String, String> parameters;

    public OAuthSignatureBuilder() {
        // Initialize the timestamp to the current time in seconds
        timestamp = System.currentTimeMillis() / 1000;

        // Generate a random and unique oauth_nonce
        nonce = generateOAuthNonce();

        // Load credentials from file
        loadCredentials();

        // Initialize the parameters
        parameters = new TreeMap<>();
        parameters.put("grant_type", "client_credentials");
        parameters.put("scope", "profile email"); // Specify the scopes you need
        parameters.put("oauth_consumer_key", getPropertyValue("here.access.key.id"));
        parameters.put("oauth_nonce", nonce);
        parameters.put("oauth_signature_method", "HMAC-SHA256");
        parameters.put("oauth_timestamp", String.valueOf(timestamp));
        parameters.put("oauth_version", "1.0");
    }

    private void loadCredentials() {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(System.getProperty("user.home") + "/.here/credentials.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set properties as system properties for easy access
        properties.forEach((key, value) -> System.setProperty((String) key, (String) value));
    }

    private String getPropertyValue(String key) {
        return System.getProperty(key);
    }

    public String buildSignatureBaseString() {
        // Create a Map to store URL-encoded parameters
        Map<String, String> encodedParameters = new TreeMap<>();

        try {
            // URL encode every key and value
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                String encodedKey = URLEncoder.encode(entry.getKey(), UTF_8);
                String encodedValue = URLEncoder.encode(entry.getValue(), UTF_8);
                encodedParameters.put(encodedKey, encodedValue);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Concatenate each key/value pair, separating each with an ampersand character ("&")
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : encodedParameters.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }

        // Remove the trailing "&" character
        sb.setLength(sb.length() - 1);

        return sb.toString();
    }

    public String buildBaseString() {
        // Combine the HTTP method, base URL, and parameter string into a single string
        String httpMethod = "POST";
        String baseUrl = "https://account.api.here.com/oauth2/token";
        String parameterString = buildSignatureBaseString();

        // URL encode the base string
        try {
            httpMethod = URLEncoder.encode(httpMethod, UTF_8);
            baseUrl = URLEncoder.encode(baseUrl, UTF_8);
            parameterString = URLEncoder.encode(parameterString, UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Concatenate the components into the final base string
        return httpMethod + "&" + baseUrl + "&" + parameterString;
    }

    public String createSigningKey() {
        String consumerSecret = getPropertyValue("here.access.key.secret");

        // URL encode the consumer secret
        try {
            consumerSecret = URLEncoder.encode(consumerSecret, UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Concatenate the URL-encoded consumer secret with an ampersand
        return consumerSecret + "&";
    }

    public String generateSignature(String baseString, String signingKey) {
        try {
            // Convert the base string and signing key to bytes
            byte[] baseStringBytes = baseString.getBytes(StandardCharsets.UTF_8);
            byte[] signingKeyBytes = signingKey.getBytes(StandardCharsets.UTF_8);

            // Create an HMAC-SHA256 key
            SecretKeySpec keySpec = new SecretKeySpec(signingKeyBytes, HMAC_SHA256);
            Mac mac = Mac.getInstance(HMAC_SHA256);
            mac.init(keySpec);

            // Compute the HMAC-SHA256 hash
            byte[] hashBytes = mac.doFinal(baseStringBytes);

            // Encode the hash to base64
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String generateOAuthNonce() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[32]; // You can adjust the length as needed for your security requirements
        secureRandom.nextBytes(randomBytes);
        return Base64.getUrlEncoder().encodeToString(randomBytes);
    }

}

