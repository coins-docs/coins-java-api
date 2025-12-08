package com.coins.api.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.lang.ref.WeakReference;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Memory-safe utility class for generating HMAC SHA256 signatures for API authentication
 * Uses bounded caching with automatic cleanup to prevent ThreadLocal memory leaks
 */
public class SignatureUtils {
    private static final String HMAC_SHA256 = "HmacSHA256";
    
    // Maximum cache size per thread to prevent unbounded growth
    private static final int MAX_CACHE_SIZE = 5;
    
    // Cache cleanup interval in minutes
    private static final int CLEANUP_INTERVAL_MINUTES = 30;
    
    // Memory-safe ThreadLocal with bounded cache and WeakReferences
    private static final ThreadLocal<ConcurrentHashMap<String, WeakReference<Mac>>> MAC_CACHE = 
        new ThreadLocal<ConcurrentHashMap<String, WeakReference<Mac>>>() {
            @Override
            protected ConcurrentHashMap<String, WeakReference<Mac>> initialValue() {
                return new ConcurrentHashMap<>();
            }
            
            @Override
            public void remove() {
                ConcurrentHashMap<String, WeakReference<Mac>> cache = get();
                if (cache != null) {
                    cache.clear();
                }
                super.remove();
            }
        };
    
    // Scheduled executor for periodic cache cleanup (daemon thread)
    private static final ScheduledExecutorService CLEANUP_EXECUTOR = 
        Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r, "SignatureUtils-Cleanup");
            t.setDaemon(true); // Daemon thread won't prevent JVM shutdown
            return t;
        });
    
    static {
        // Schedule periodic cleanup of stale cache entries
        CLEANUP_EXECUTOR.scheduleAtFixedRate(
            SignatureUtils::cleanupStaleEntries, 
            CLEANUP_INTERVAL_MINUTES, 
            CLEANUP_INTERVAL_MINUTES, 
            TimeUnit.MINUTES
        );
        
        // Add shutdown hook to clean up resources
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            CLEANUP_EXECUTOR.shutdown();
            try {
                if (!CLEANUP_EXECUTOR.awaitTermination(5, TimeUnit.SECONDS)) {
                    CLEANUP_EXECUTOR.shutdownNow();
                }
            } catch (InterruptedException e) {
                CLEANUP_EXECUTOR.shutdownNow();
                Thread.currentThread().interrupt();
            }
            clearAllCaches();
        }));
    }

    /**
     * Generate HMAC SHA256 signature with memory-safe Mac instance caching
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
     * Get cached Mac instance for the given secret key with memory-safe caching
     * Uses WeakReference to allow garbage collection and bounded cache size
     */
    private static Mac getCachedMac(String secretKey) throws NoSuchAlgorithmException, InvalidKeyException {
        ConcurrentHashMap<String, WeakReference<Mac>> cache = MAC_CACHE.get();
        
        // Use secret key hash as cache key to avoid storing the actual secret key
        String cacheKey = Integer.toString(secretKey.hashCode());
        
        // Try to get from cache
        WeakReference<Mac> macRef = cache.get(cacheKey);
        Mac mac = null;
        
        if (macRef != null) {
            mac = macRef.get();
        }
        
        // If not in cache or garbage collected, create new instance
        if (mac == null) {
            mac = Mac.getInstance(HMAC_SHA256);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), HMAC_SHA256);
            mac.init(secretKeySpec);
            
            // Enforce cache size limit to prevent memory issues
            if (cache.size() >= MAX_CACHE_SIZE) {
                // Remove oldest entry (simple eviction strategy)
                cache.entrySet().iterator().next();
                cache.entrySet().iterator().remove();
            }
            
            cache.put(cacheKey, new WeakReference<>(mac));
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
        ConcurrentHashMap<String, WeakReference<Mac>> cache = MAC_CACHE.get();
        if (cache != null) {
            cache.clear();
        }
    }
    
    /**
     * Clear all thread-local caches (for shutdown cleanup)
     */
    private static void clearAllCaches() {
        MAC_CACHE.remove();
    }
    
    /**
     * Clean up stale cache entries (called periodically)
     */
    private static void cleanupStaleEntries() {
        try {
            ConcurrentHashMap<String, WeakReference<Mac>> cache = MAC_CACHE.get();
            if (cache != null) {
                // Remove entries where WeakReference has been garbage collected
                cache.entrySet().removeIf(entry -> entry.getValue().get() == null);
            }
        } catch (Exception e) {
            // Ignore cleanup errors to prevent disrupting main functionality
        }
    }

    /**
     * Convert byte array to hex string
     *
     * @param bytes The byte array
     * @return The hex string
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder(bytes.length * 2);
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
