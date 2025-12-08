package com.coins.api.client.base;

import com.coins.api.client.CoinsApiConfig;
import com.coins.api.exception.CoinsApiException;
import com.coins.api.util.SignatureUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * HTTP client for making API requests
 */
public class HttpClient {
    private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final MediaType FORM_URLENCODED = MediaType.get("application/x-www-form-urlencoded; charset=utf-8");

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;
    private final CoinsApiConfig config;

    public HttpClient(CoinsApiConfig config) {
        this.config = config;
        this.objectMapper = new ObjectMapper();
        // Optimized HTTP client configuration with connection pool
        this.client = new OkHttpClient.Builder()
                // Connection timeouts
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)

                // Connection pool configuration for better performance
                // Keep up to 10 idle connections alive for 5 minutes
                .connectionPool(new okhttp3.ConnectionPool(10, 5, TimeUnit.MINUTES))

                // Enable connection reuse and retry on connection failure
                .retryOnConnectionFailure(true)

                // Follow redirects
                .followRedirects(true)
                .followSslRedirects(true)

                .build();
    }

    /**
     * Execute GET request
     */
    public <T> T get(String endpoint, String queryString, TypeReference<T> responseType) throws CoinsApiException {
        String url = buildUrl(endpoint, queryString);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-COINS-APIKEY", config.getApiKey())
                .build();

        return executeRequest(request, responseType);
    }

    /**
     * Execute POST request
     */
    public <T> T post(String endpoint, String queryString, TypeReference<T> responseType) throws CoinsApiException {
        String url = buildUrl(endpoint, queryString);
        RequestBody body = RequestBody.create("", JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("X-COINS-APIKEY", config.getApiKey())
                .build();

        return executeRequest(request, responseType);
    }

    /**
     * Execute POST request with URL-encoded form body
     * This method is specifically designed for APIs that require URL-encoded request body
     * with proper signature calculation as per Example 3 documentation
     */
    public <T> T postWithBody(String endpoint, Object requestBody, TypeReference<T> responseType) throws CoinsApiException {
        return postWithBody(endpoint, null, requestBody, responseType);
    }

    /**
     * Execute POST request with mixed query string and URL-encoded form body
     * Based on Example 3: Mixed query string and request body from the API documentation
     * This method converts the request object to URL-encoded format for proper signature calculation
     */
    public <T> T postWithBody(String endpoint, String queryString, Object requestBody, TypeReference<T> responseType) throws CoinsApiException {
        try {
            // Convert request body to map and add timestamp/recvWindow
            @SuppressWarnings("unchecked")
            Map<String, Object> requestMap = objectMapper.convertValue(requestBody, Map.class);

            requestMap.put("timestamp", System.currentTimeMillis());
            if (config.getRecvWindow() > 0) {
                requestMap.put("recvWindow", config.getRecvWindow());
            }

            // Use TreeMap to ensure consistent alphabetical ordering of parameters
            // This is crucial for signature generation consistency
            TreeMap<String, Object> sortedParams = new TreeMap<>();
            for (Map.Entry<String, Object> entry : requestMap.entrySet()) {
                if (entry.getValue() != null) {
                    sortedParams.put(entry.getKey(), entry.getValue());
                }
            }

            // Build signature data string with consistent parameter ordering
            StringBuilder signatureData = new StringBuilder();
            boolean first = true;
            for (Map.Entry<String, Object> entry : sortedParams.entrySet()) {
                if (!first) {
                    signatureData.append("&");
                }
                first = false;
                signatureData.append(entry.getKey()).append("=").append(entry.getValue().toString());
            }

            // Generate signature
            String signature = SignatureUtils.generateSignature(signatureData.toString(), config.getSecretKey());
            sortedParams.put("signature", signature);

            // Build form body using the same sorted parameters to ensure consistency
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String, Object> entry : sortedParams.entrySet()) {
                builder.add(entry.getKey(), entry.getValue().toString());
            }

            String url = config.getBaseUrl() + "/" + endpoint;

            Request request = new Request.Builder()
                    .url(url)
                    .post(builder.build())
                    .addHeader("X-COINS-APIKEY", config.getApiKey())
                    .build();

            return executeRequest(request, responseType);
        } catch (Exception e) {
            throw new CoinsApiException("Error creating URL-encoded request body: " + e.getMessage(), e);
        }
    }


    /**
     * Execute DELETE request
     */
    public <T> T delete(String endpoint, String queryString, TypeReference<T> responseType) throws CoinsApiException {
        String url = buildUrl(endpoint, queryString);
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .addHeader("X-COINS-APIKEY", config.getApiKey())
                .build();

        return executeRequest(request, responseType);
    }

    /**
     * Build complete URL with signature
     */
    private String buildUrl(String endpoint, String queryString) {
        long timestamp = System.currentTimeMillis();

        // Add timestamp and recvWindow to query string
        StringBuilder sb = new StringBuilder();
        if (queryString != null && !queryString.isEmpty()) {
            sb.append(queryString);
            sb.append("&");
        }
        sb.append("timestamp=").append(timestamp);
        if (config.getRecvWindow() > 0) {
            sb.append("&recvWindow=").append(config.getRecvWindow());
        }

        // Generate signature
        String signatureString = SignatureUtils.buildSignatureString(sb.toString());
        String signature = SignatureUtils.generateSignature(signatureString, config.getSecretKey());
        sb.append("&signature=").append(signature);

        return config.getBaseUrl() + "/" + endpoint + "?" + sb.toString();
    }

    /**
     * Execute HTTP request and parse response
     */
    private <T> T executeRequest(Request request, TypeReference<T> responseType) throws CoinsApiException {
        try {
            logger.debug("Executing request: {} {}", request.method(), request.url());

            try (Response response = client.newCall(request).execute()) {
                String responseBody = response.body() != null ? response.body().string() : "";

                logger.debug("Response: {} - {}", response.code(), responseBody);

                if (!response.isSuccessful()) {
                    throw new CoinsApiException(response.code(), "HTTP error: " + response.code() + " - " + responseBody);
                }

                if (responseBody.isEmpty()) {
                    return null;
                }

                // Check if response contains an API error (code field with negative value)
                if (responseBody.contains("\"code\"") || responseBody.contains("\"status\"")) {
                    // Try to parse as error response to extract code and message
                    try {
                        com.fasterxml.jackson.databind.JsonNode jsonNode = objectMapper.readTree(responseBody);
                        if (jsonNode.has("code")) {
                            int errorCode = jsonNode.get("code").asInt();
                            String errorMsg = jsonNode.has("msg") ? jsonNode.get("msg").asText() : "Unknown error";
                            throw new CoinsApiException(errorCode, errorMsg);
                        } else if (jsonNode.has("status")) {
                            int errorCode = jsonNode.get("status").asInt();
                            if (errorCode != 0) {
                                String errorMsg = jsonNode.has("error") ? jsonNode.get("error").asText() : "Unknown error";
                                throw new CoinsApiException(errorCode, errorMsg);
                            }
                        }
                    } catch (Exception parseEx) {
                        // If parsing fails, throw the original response as error
                        throw new CoinsApiException(response.code(), responseBody);
                    }
                }
                return objectMapper.readValue(responseBody, responseType);
            }
        } catch (IOException e) {
            throw new CoinsApiException("Network error: " + e.getMessage(), e);
        }
    }
}
