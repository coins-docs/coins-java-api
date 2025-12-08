package com.coins.api.client;

/**
 * Configuration class for Coins API client
 */
public class CoinsApiConfig {
    private final String baseUrl;
    private final String apiKey;
    private final String secretKey;
    private final long recvWindow;

    private CoinsApiConfig(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.apiKey = builder.apiKey;
        this.secretKey = builder.secretKey;
        this.recvWindow = builder.recvWindow;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public long getRecvWindow() {
        return recvWindow;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String baseUrl = "https://api.coins.ph";
        private String apiKey;
        private String secretKey;
        private long recvWindow = 5000;

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Builder secretKey(String secretKey) {
            this.secretKey = secretKey;
            return this;
        }

        public Builder recvWindow(long recvWindow) {
            this.recvWindow = recvWindow;
            return this;
        }

        public CoinsApiConfig build() {
            if (apiKey == null || apiKey.trim().isEmpty()) {
                throw new IllegalArgumentException("API key is required");
            }
            if (secretKey == null || secretKey.trim().isEmpty()) {
                throw new IllegalArgumentException("Secret key is required");
            }
            return new CoinsApiConfig(this);
        }
    }
}
