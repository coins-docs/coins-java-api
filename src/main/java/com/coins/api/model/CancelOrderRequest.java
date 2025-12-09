package com.coins.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.AssertTrue;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CancelOrderRequest {
    
    @Min(value = 1, message = "orderId must be greater than 0")
    private Long orderId;
    
    private String origClientOrderId;
    
    /**
     * Validation method to ensure at least one order identifier is provided
     */
    @AssertTrue(message = "Either orderId or origClientOrderId is required")
    public boolean isValidOrderIdentifier() {
        return (orderId != null && orderId > 0) || 
               (origClientOrderId != null && !origClientOrderId.trim().isEmpty());
    }
}
