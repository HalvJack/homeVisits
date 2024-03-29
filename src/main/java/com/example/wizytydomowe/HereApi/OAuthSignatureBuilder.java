package com.example.wizytydomowe.HereApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;

public class OAuthSignatureBuilder {

    private static final String UTF_8 = "UTF-8";
    private static final String HMAC_SHA256 = "HmacSHA256";

    private static final Logger logger = LoggerFactory.getLogger(OAuthSignatureBuilder.class);

    private long timestamp;
    private String nonce;
    private SortedMap<String, String> parameters;

    private String createdSignatureBaseString;
    private String baseString;

    private String extendedSecretKey;
    private String secretKey;

    private String signature;

    private String authorizationHeader;

    public OAuthSignatureBuilder(String accessKeyId, String secretKey) {
        // Initialize the timestamp to the current time in seconds
        timestamp = System.currentTimeMillis() / 1000;

        // Generate a random and unique oauth_nonce
        nonce = generateOAuthNonce();

        // Initialize the parameters
        parameters = new TreeMap<>();
        parameters.put("grant_type", "client_credentials");
        parameters.put("oauth_consumer_key", accessKeyId);
        parameters.put("oauth_nonce", nonce);
        parameters.put("oauth_signature_method", HMAC_SHA256);
        parameters.put("oauth_timestamp", String.valueOf(timestamp));
        parameters.put("oauth_version", "1.0");

        createdSignatureBaseString = buildSignatureBaseString();
        baseString = createTheBaseString(createdSignatureBaseString);
        this.secretKey = secretKey;
        extendedSecretKey = secretKey+"&";
        signature = generateSignature(baseString, extendedSecretKey);

        authorizationHeader = "Basic " + Base64.encodeBase64String((accessKeyId + ":" + secretKey).getBytes());

    }
    public Map<String, String> getOAuthParameters() {
        return new TreeMap<>(parameters); // Return a copy to prevent modification outside the class
    }

    public String buildSignatureBaseString() {
        StringBuilder sb = new StringBuilder();

        try {
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                String encodedKey = URLEncoder.encode(entry.getKey(), UTF_8);
                String encodedValue = URLEncoder.encode(entry.getValue(), UTF_8);
                sb.append(encodedKey).append("%").append(encodedValue).append("%");
            }
            // Remove the trailing "&" character
            sb.setLength(sb.length() - 1);

        } catch (UnsupportedEncodingException e) {
            // Log the exception as an error
            logger.error("Error encoding parameters", e);
            // Continue with the flow, optionally you can rethrow or handle it based on your application's needs
        }
        return sb.toString();
    }

   private String createTheBaseString(String signatureBaseString){
        String titleString = "POST&https%3A%2F%2Faccount.api.here.com%2Foauth2%2Ftoken&";
        return titleString + signatureBaseString;
   }

    private String generateOAuthNonce() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[32]; // You can adjust the length as needed for your security requirements
        secureRandom.nextBytes(randomBytes);
        return Base64.encodeBase64String(randomBytes);
    }

    public String getCreatedSignatureBaseString() {
        return createdSignatureBaseString;
    }

    public String getBaseString() {
        return baseString;
    }

    private static String generateSignature(String baseString, String signingKey) {
        try {
            Mac mac = Mac.getInstance(HMAC_SHA256);
            SecretKeySpec secretKeySpec = new SecretKeySpec(signingKey.getBytes(StandardCharsets.UTF_8), HMAC_SHA256);
            mac.init(secretKeySpec);

            byte[] hashBytes = mac.doFinal(baseString.getBytes(StandardCharsets.UTF_8));
            return Base64.encodeBase64String(hashBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getSignature() {
        return signature;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getAuthorizationHeader() {
        return authorizationHeader;
    }
}

