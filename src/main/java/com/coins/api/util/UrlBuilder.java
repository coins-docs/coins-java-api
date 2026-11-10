package com.coins.api.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

/**
 * Optimized URL builder utility for better performance
 * Reduces string concatenation overhead and provides efficient parameter handling
 */
public class UrlBuilder {
    private final StringBuilder baseUrl;
    private final TreeMap<String, String> parameters;
    private boolean hasQuery = false;

    /**
     * Create a new URL builder with base URL
     */
    public UrlBuilder(String baseUrl) {
        this.baseUrl = new StringBuilder(baseUrl);
        this.parameters = new TreeMap<>(); // TreeMap ensures consistent ordering for signatures
    }

    /**
     * Add a parameter to the URL
     */
    public UrlBuilder addParameter(String name, Object value) {
        if (value != null) {
            parameters.put(name, value.toString());
        }
        return this;
    }

    /**
     * Add multiple parameters from a map
     */
    public UrlBuilder addParameters(Map<String, Object> params) {
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                addParameter(entry.getKey(), entry.getValue());
            }
        }
        return this;
    }

    /**
     * Add parameter only if condition is true
     */
    public UrlBuilder addParameterIf(boolean condition, String name, Object value) {
        if (condition && value != null) {
            parameters.put(name, value.toString());
        }
        return this;
    }

    /**
     * Build the complete URL with all parameters
     */
    public String build() {
        if (parameters.isEmpty()) {
            return baseUrl.toString();
        }

        StringBuilder result = new StringBuilder(baseUrl);
        result.append('?');

        boolean first = true;
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            if (!first) {
                result.append('&');
            }
            first = false;
            
            result.append(entry.getKey())
                  .append('=')
                  .append(urlEncode(entry.getValue()));
        }

        return result.toString();
    }

    /**
     * Build query string only (without base URL)
     */
    public String buildQueryString() {
        if (parameters.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        boolean first = true;
        
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            if (!first) {
                result.append('&');
            }
            first = false;
            
            result.append(entry.getKey())
                  .append('=')
                  .append(entry.getValue()); // No URL encoding for signature calculation
        }

        return result.toString();
    }

    /**
     * Get the sorted parameters map (useful for signature generation)
     */
    public TreeMap<String, String> getParameters() {
        return new TreeMap<>(parameters);
    }

    /**
     * Clear all parameters
     */
    public UrlBuilder clearParameters() {
        parameters.clear();
        return this;
    }

    /**
     * URL encode a value
     */
    private String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            // This should never happen with UTF-8
            throw new RuntimeException("UTF-8 encoding not supported", e);
        }
    }

    /**
     * Static factory method for convenience
     */
    public static UrlBuilder create(String baseUrl) {
        return new UrlBuilder(baseUrl);
    }

    /**
     * Static method to build query string from parameters efficiently
     */
    public static String buildQueryString(Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            return "";
        }

        // Use TreeMap for consistent ordering
        TreeMap<String, Object> sortedParams = new TreeMap<>(params);
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (Map.Entry<String, Object> entry : sortedParams.entrySet()) {
            if (entry.getValue() != null) {
                if (!first) {
                    result.append('&');
                }
                first = false;
                result.append(entry.getKey())
                      .append('=')
                      .append(entry.getValue().toString());
            }
        }

        return result.toString();
    }
}
