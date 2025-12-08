package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    
    @NotEmpty
    private String payerContactInfo;
    
    @NotEmpty
    private String receivingAccount;
    
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal amount;
    
    @NotEmpty
    private String message;
    
    private String supportedPaymentCollectors;
    
    private String expiresAt;
}
