package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePaymentRequestRequest {
    
    @NotEmpty(message = "payerContactInfo cannot be empty")
    private String payerContactInfo;
    
    @NotEmpty(message = "receivingAccount cannot be empty")
    private String receivingAccount;
    
    @NotNull(message = "amount cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "amount must be greater than 0")
    private BigDecimal amount;
    
    @NotEmpty(message = "message cannot be empty")
    private String message;
    
    private String supportedPaymentCollectors;
    
    private String expiresAt;
}
