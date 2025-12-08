package com.coins.api.util;

import com.coins.api.exception.CoinsApiException;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;

/**
 * Validation utility for manual validation
 */
public class ValidationUtil {
    
    private static volatile ValidatorFactory factory;
    private static volatile Validator validator;
    
    // Initialize factory and validator lazily with proper resource management
    static {
        initializeValidator();
        // Register shutdown hook to properly close the factory
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (factory != null) {
                try {
                    factory.close();
                } catch (Exception e) {
                    // Log error if logging is available, otherwise ignore
                    System.err.println("Warning: Failed to close ValidatorFactory: " + e.getMessage());
                }
            }
        }));
    }
    
    /**
     * Initialize the validator factory and validator instance
     */
    private static synchronized void initializeValidator() {
        if (factory == null) {
            factory = Validation.byDefaultProvider()
                    .configure()
                    .messageInterpolator(new ResourceBundleMessageInterpolator())
                    .buildValidatorFactory();
            validator = factory.getValidator();
        }
    }
    
    /**
     * Get the validator instance, initializing if necessary
     */
    private static Validator getValidator() {
        if (validator == null) {
            synchronized (ValidationUtil.class) {
                if (validator == null) {
                    initializeValidator();
                }
            }
        }
        return validator;
    }
    
    /**
     * Manually close the validator factory (for testing or explicit cleanup)
     * This method is primarily for testing purposes or when you need explicit control
     * over resource cleanup. In normal usage, the shutdown hook will handle cleanup.
     */
    public static synchronized void closeValidatorFactory() {
        if (factory != null) {
            try {
                factory.close();
            } catch (Exception e) {
                System.err.println("Warning: Failed to close ValidatorFactory: " + e.getMessage());
            } finally {
                factory = null;
                validator = null;
            }
        }
    }
    
    /**
     * Validate an object and throw exception if validation fails
     * 
     * @param object Object to validate
     * @throws CoinsApiException if validation fails
     */
    public static <T> void validate(T object) throws CoinsApiException {
        Set<ConstraintViolation<T>> violations = getValidator().validate(object);
        
        if (!violations.isEmpty()) {
            String errorMessage = violations.stream()
                    .map(violation -> {
                        // Get the message with English locale
                        String message = violation.getMessage();
                        return violation.getPropertyPath() + ": " + message;
                    })
                    .collect(Collectors.joining(", "));
            
            throw new CoinsApiException("Validation failed: " + errorMessage);
        }
    }
    
    /**
     * Validate an object with specific locale and throw exception if validation fails
     * 
     * @param object Object to validate
     * @param locale Locale for validation messages
     * @throws CoinsApiException if validation fails
     */
    public static <T> void validate(T object, Locale locale) throws CoinsApiException {
        Set<ConstraintViolation<T>> violations = getValidator().validate(object);
        
        if (!violations.isEmpty()) {
            String errorMessage = violations.stream()
                    .map(violation -> {
                        // For English messages, we'll provide custom messages for common validations
                        String message = getEnglishMessage(violation, locale);
                        return violation.getPropertyPath() + ": " + message;
                    })
                    .collect(Collectors.joining(", "));
            
            throw new CoinsApiException("Validation failed: " + errorMessage);
        }
    }
    
    private static <T> String getEnglishMessage(ConstraintViolation<T> violation, Locale locale) {
        String annotationType = violation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName();
        
        switch (annotationType) {
            case "NotNull":
                return "must not be null";
            case "NotBlank":
                return "must not be blank";
            case "DecimalMin":
                Object minValue = violation.getConstraintDescriptor().getAttributes().get("value");
                Boolean inclusive = (Boolean) violation.getConstraintDescriptor().getAttributes().get("inclusive");
                if (inclusive != null && !inclusive) {
                    return "must be greater than " + minValue;
                } else {
                    return "must be greater than or equal to " + minValue;
                }
            case "Length":
                Object max = violation.getConstraintDescriptor().getAttributes().get("max");
                return "length must be less than or equal to " + max;
            case "Min":
                Object minVal = violation.getConstraintDescriptor().getAttributes().get("value");
                return "must be greater than or equal to " + minVal;
            case "Max":
                Object maxVal = violation.getConstraintDescriptor().getAttributes().get("value");
                return "must be less than or equal to " + maxVal;
            default:
                return violation.getMessage();
        }
    }
    
}
