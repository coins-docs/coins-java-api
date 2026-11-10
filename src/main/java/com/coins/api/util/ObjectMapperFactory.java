package com.coins.api.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.core.JsonFactory;

/**
 * Optimized ObjectMapper factory with performance tuning and singleton pattern
 * Provides a shared, thread-safe ObjectMapper instance with optimal configuration
 */
public class ObjectMapperFactory {
    
    private static volatile ObjectMapper instance;
    private static final Object lock = new Object();
    
    /**
     * Get the singleton ObjectMapper instance with performance optimizations
     * Uses double-checked locking for thread safety and performance
     */
    public static ObjectMapper getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = createOptimizedObjectMapper();
                }
            }
        }
        return instance;
    }
    
    /**
     * Create an optimized ObjectMapper with performance tuning
     */
    private static ObjectMapper createOptimizedObjectMapper() {
        // Use optimized JsonFactory for better performance
        JsonFactory jsonFactory = new JsonFactory();
        
        // Enable factory optimizations
        jsonFactory.configure(JsonFactory.Feature.INTERN_FIELD_NAMES, true);
        jsonFactory.configure(JsonFactory.Feature.CANONICALIZE_FIELD_NAMES, true);
        
        ObjectMapper mapper = new ObjectMapper(jsonFactory);
        
        // Performance optimizations
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);
        
        // Disable features that can slow down serialization
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        
        // Enable features for better performance
        mapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, false);
        mapper.configure(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS, false);
        
        // Optimize for API response parsing
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        
        return mapper;
    }
    
    /**
     * Create a new ObjectMapper instance with the same optimizations
     * Use this only when you need a separate instance (rare cases)
     */
    public static ObjectMapper createNew() {
        return createOptimizedObjectMapper();
    }
    
    /**
     * Private constructor to prevent instantiation
     */
    private ObjectMapperFactory() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}
