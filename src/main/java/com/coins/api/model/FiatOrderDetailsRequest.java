package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.AssertTrue;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FiatOrderDetailsRequest {
    
    /**
     * Internal order ID (required if externalOrderId is not provided)
     */
    private String internalOrderId;
    
    /**
     * External order ID (required if internalOrderId is not provided)
     */
    private String externalOrderId;
    
    /**
     * Validation method to ensure at least one order ID is provided
     */
    @AssertTrue(message = "Either internalOrderId or externalOrderId must be provided")
    public boolean isValidOrderId() {
        return (internalOrderId != null && !internalOrderId.trim().isEmpty()) || 
               (externalOrderId != null && !externalOrderId.trim().isEmpty());
    }
}
