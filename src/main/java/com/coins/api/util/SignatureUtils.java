package com.coins.api.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ConcurrentHashMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Optimized utility class for generating HMAC SHA256 signatures for API authentication
 * Uses ThreadLocal Mac instances for better performance in multi-threaded environments
 */
public class SignatureUtils {
    private static final String HMAC_SHA256 = "HmacSHA256";
    
    // ThreadLocal cache for Mac instances to avoid repeated creation and ensure thread safety
    private static final ThreadLocal<ConcurrentHashMap<String, Mac>> MAC_CACHE = 
        ThreadLocal.withInitial(ConcurrentHashMap::new);

    /**
     * Generate HMAC SHA256 signature with optimized Mac instance caching
     *
     * @param data      The data to sign
     * @param secretKey The secret key
     * @return The hex-encoded signature
     */
    public static String generateSignature(String data, String secretKey) {
        try {
            Mac mac = getCachedMac(secretKey);
            byte[] hash = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to generate signature", e);
        }
    }
    
    /**
     * Get cached Mac instance for the given secret key
     * Uses ThreadLocal cache to ensure thread safety and performance
     */
    private static Mac getCachedMac(String secretKey) throws NoSuchAlgorithmException, InvalidKeyException {
        ConcurrentHashMap<String, Mac> cache = MAC_CACHE.get();
        
        // Use secret key hash as cache key to avoid storing the actual secret key
        String cacheKey = Integer.toString(secretKey.hashCode());
        
        Mac mac = cache.get(cacheKey);
        if (mac == null) {
            mac = Mac.getInstance(HMAC_SHA256);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), HMAC_SHA256);
            mac.init(secretKeySpec);
            cache.put(cacheKey, mac);
        } else {
            // Reset the Mac instance for reuse
            mac.reset();
        }
        
        return mac;
    }
    
    /**
     * Clear the Mac cache for the current thread
     * Useful for cleanup in long-running applications
     */
    public static void clearCache() {
        MAC_CACHE.get().clear();
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
