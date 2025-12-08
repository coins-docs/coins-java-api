package com.coins.api.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Utility class for generating HMAC SHA256 signatures for API authentication
 */
public class SignatureUtils {
    private static final String HMAC_SHA256 = "HmacSHA256";

    /**
     * Generate HMAC SHA256 signature
     *
     * @param data      The data to sign
     * @param secretKey The secret key
     * @return The hex-encoded signature
     */
    public static String generateSignature(String data, String secretKey) {
        try {
            Mac mac = Mac.getInstance(HMAC_SHA256);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), HMAC_SHA256);
            mac.init(secretKeySpec);
            byte[] hash = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to generate signature", e);
        }
    }

    /**
     * Convert byte array to hex string
     *
     * @param bytes The byte array
     * @return The hex string
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    /**
     * Build query string from parameters for signature generation
     *
     * @param queryString The query string with parameters
     * @return The formatted query string for signature
     */
    public static String buildSignatureString(String queryString) {
        if (queryString == null || queryString.isEmpty()) {
            return "";
        }
        
        // Remove signature parameter if present
        String[] params = queryString.split("&");
        StringBuilder sb = new StringBuilder();
        
        for (String param : params) {
            if (!param.startsWith("signature=")) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(param);
            }
        }
        
        return sb.toString();
    }
}
